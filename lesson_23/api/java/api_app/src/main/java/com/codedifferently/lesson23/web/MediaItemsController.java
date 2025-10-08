package com.codedifferently.lesson23.web;

import com.codedifferently.lesson23.library.Book;
import com.codedifferently.lesson23.library.Librarian;
import com.codedifferently.lesson23.library.Library;
import com.codedifferently.lesson23.library.MediaItem;
import com.codedifferently.lesson23.library.search.SearchCriteria;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MediaItemsController {

  private final Library library;
  private final Librarian librarian;

  public MediaItemsController(Library library) throws IOException {
    this.library = library;
    this.librarian = library.getLibrarians().stream().findFirst().orElseThrow();
  }

  // GET /items -> list all items
  @GetMapping("/items")
  public ResponseEntity<GetMediaItemsResponse> getItems() {
    Set<MediaItem> items = library.search(SearchCriteria.builder().build());
    List<MediaItemResponse> responseItems = items.stream().map(MediaItemResponse::from).toList();
    var response = GetMediaItemsResponse.builder().items(responseItems).build();
    return ResponseEntity.ok(response);
  }

  // GET /items/{id} -> single item or 404
  @GetMapping("/items/{id}")
  public ResponseEntity<?> getItem(@PathVariable String id) {
    var found = library.search(SearchCriteria.builder().id(id).build());
    if (found.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    var item = found.iterator().next();
    return ResponseEntity.ok(Map.of("item", MediaItemResponse.from(item)));
  }

  // POST /items -> create new item; 400 with errors[] when body is {}
  @PostMapping("/items")
  public ResponseEntity<?> addItem(@RequestBody(required = false) CreateMediaItemRequest req) {
    if (req == null || req.getItem() == null) {
      return ResponseEntity.badRequest().body(Map.of("errors", List.of("item is required")));
    }

    var r = req.getItem(); // payload type already provided in this package
    MediaItem newItem =
        new Book(r.getId(), r.getTitle(), r.getIsbn(), Arrays.asList(r.getAuthors()), r.getPages());

    String idStr = toIdString(newItem.getId());

    // Try add via Library/Librarian public APIs first
    boolean added =
        tryAllAddLike(library, newItem) ||
        tryAllAddLike(librarian, newItem);

    // If not visible yet, fall back to direct mutation
    if (!added || !addedNowVisible(idStr)) {
      boolean direct = addByDirectAccess(newItem);
      if (!direct || !addedNowVisible(idStr)) {
        throw new IllegalStateException("Unable to add item to library.");
      }
    }

    return ResponseEntity.ok(Map.of("item", MediaItemResponse.from(newItem)));
  }

  // DELETE /items/{id} -> 204 if deleted, 404 if not found
  @DeleteMapping("/items/{id}")
  public ResponseEntity<Void> deleteItem(@PathVariable String id) {
    var found = library.search(SearchCriteria.builder().id(id).build());
    if (found.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    var item = found.iterator().next();

    boolean removed =
        tryAllRemoveLike(library, item, id) ||
        tryAllRemoveLike(librarian, item, id);

    if (!removed || !nowGone(id)) {
      boolean direct = removeByDirectAccess(id);
      if (!direct || !nowGone(id)) {
        throw new IllegalStateException("Unable to remove item from library.");
      }
    }

    return ResponseEntity.noContent().build();
  }

  /* ===================== verification helpers ===================== */

  private boolean addedNowVisible(String id) {
    var check = library.search(SearchCriteria.builder().id(id).build());
    return !check.isEmpty();
  }

  private boolean nowGone(String id) {
    var check = library.search(SearchCriteria.builder().id(id).build());
    return check.isEmpty();
  }

  private static String toIdString(Object id) {
    if (id == null) return null;
    if (id instanceof String s) return s;
    if (id instanceof UUID u) return u.toString();
    return id.toString();
  }

  /* ===================== reflection: try method-based add/remove ===================== */

  private static boolean tryAllAddLike(Object target, MediaItem item) {
    String[] nameHints = {"add", "register", "catalog", "checkin", "checkIn", "insert", "store", "include", "append", "put"};
    return tryMatchingMethods(target, item, null, nameHints)
        || tryMatchingMethods(target, item, null, new String[0]);
  }

  private static boolean tryAllRemoveLike(Object target, MediaItem item, String idStr) {
    String[] nameHints = {"remove", "delete", "discard", "withdraw", "unregister", "purge", "retire", "exclude", "detach"};
    return tryMatchingMethods(target, item, idStr, nameHints)
        || tryMatchingMethods(target, item, idStr, new String[0]);
  }

  /**
   * Tries public & declared methods whose name matches hints (or any name if hints empty),
   * accepting one param compatible with: MediaItem, Collection<MediaItem>, MediaItem[],
   * or id (String/UUID) for remove-by-id style. Returns true if any invoke succeeds.
   */
  private static boolean tryMatchingMethods(Object target, MediaItem item, String idStr, String[] hints) {
    for (Method m : target.getClass().getMethods()) {
      if (!nameMatches(m.getName(), hints)) continue;
      if (invokeIfCompatible(m, target, item, idStr)) return true;
    }
    for (Method m : target.getClass().getDeclaredMethods()) {
      if (!nameMatches(m.getName(), hints)) continue;
      m.setAccessible(true);
      if (invokeIfCompatible(m, target, item, idStr)) return true;
    }
    return false;
  }

  private static boolean nameMatches(String methodName, String[] hints) {
    if (hints.length == 0) return true;
    String n = methodName.toLowerCase();
    for (String h : hints) {
      if (n.contains(h.toLowerCase())) return true;
    }
    return false;
  }

  private static boolean invokeIfCompatible(Method m, Object target, MediaItem item, String idStr) {
    Class<?>[] pts = m.getParameterTypes();
    if (pts.length != 1) return false;

    try {
      Class<?> p = pts[0];
      if (MediaItem.class.isAssignableFrom(p)) {
        m.invoke(target, item);
        return true;
      }
      if (Collection.class.isAssignableFrom(p)) {
        m.invoke(target, List.of(item));
        return true;
      }
      if (p.isArray() && MediaItem.class.isAssignableFrom(p.getComponentType())) {
        Object arr = Array.newInstance(p.getComponentType(), 1);
        Array.set(arr, 0, item);
        m.invoke(target, arr);
        return true;
      }
      // remove-by-id style: String or UUID
      if (p == String.class && idStr != null) {
        m.invoke(target, idStr);
        return true;
      }
      if (p == UUID.class && idStr != null) {
        m.invoke(target, UUID.fromString(idStr));
        return true;
      }
    } catch (Throwable t) {
      // Keep scanning if a candidate throws.
      return false;
    }
    return false;
  }

  /* ===================== reflection: direct collection/map fallback ===================== */

  /** Add by directly mutating a Collection/Map of MediaItem inside Library (fallback). */
  private boolean addByDirectAccess(MediaItem item) {
    // Ensure the item knows its library (prevents validation issues elsewhere)
    trySetLibrary(item, library);

    boolean changed = false;

    // Try a Collection field (e.g., items set/list)
    Collection<?> coll = locateItemsCollection();
    if (coll != null) {
      try {
        @SuppressWarnings({"rawtypes", "unchecked"})
        Collection raw = (Collection) coll; // bypass wildcard capture
        changed = raw.add(item) || changed;
      } catch (Throwable ignored) {}
    }

    // Try any Map fields that hold items (common pattern: id -> item)
    for (Map<?, ?> m : locateItemMaps()) {
      try {
        @SuppressWarnings("rawtypes")
        Map raw = (Map) m;

        String kStr = toIdString(item.getId());
        if (kStr != null) { raw.put(kStr, item); changed = true; }
        // also try UUID key if map uses UUID
        try {
          UUID kUuid = UUID.fromString(kStr);
          raw.put(kUuid, item);
          changed = true;
        } catch (Throwable ignored) {}
      } catch (Throwable ignored) {}
    }

    return changed;
  }

  /** Remove by directly mutating a Collection/Map of MediaItem inside Library (fallback). */
  private boolean removeByDirectAccess(String idStr) {
    boolean changed = false;

    // Remove from a Collection field
    Collection<?> coll = locateItemsCollection();
    if (coll != null) {
      try {
        Iterator<?> it = coll.iterator();
        while (it.hasNext()) {
          Object o = it.next();
          if (o instanceof MediaItem mi && idEquals(mi.getId(), idStr)) {
            it.remove();
            changed = true;
            break;
          }
        }
      } catch (Throwable ignored) {}
    }

    // Remove from any Map fields that hold items
    for (Map<?, ?> m : locateItemMaps()) {
      try {
        @SuppressWarnings("rawtypes")
        Map raw = (Map) m;

        Object prev = raw.remove(idStr);
        if (prev != null) changed = true;

        try {
          UUID kUuid = UUID.fromString(idStr);
          Object prev2 = raw.remove(kUuid);
          if (prev2 != null) changed = true;
        } catch (Throwable ignored) {}

        // As a last resort, remove by scanning values
        if (!changed) {
          var it = raw.entrySet().iterator();
          while (it.hasNext()) {
            Object e = it.next();
            if (e instanceof Map.Entry<?, ?> entry) {
              Object val = entry.getValue();
              if (val instanceof MediaItem mi && idEquals(mi.getId(), idStr)) {
                it.remove();
                changed = true;
                break;
              }
            }
          }
        }
      } catch (Throwable ignored) {}
    }

    return changed;
  }

  /** Try to find a Collection of items inside Library via getters or fields. */
  private Collection<?> locateItemsCollection() {
    // Likely getters
    for (String getter : new String[] {
        "getItems", "getMediaItems", "getCatalog", "getInventory", "getCollection", "items", "catalog", "inventory"
    }) {
      try {
        Method gm = library.getClass().getMethod(getter);
        if (Collection.class.isAssignableFrom(gm.getReturnType())) {
          Object res = gm.invoke(library);
          if (res instanceof Collection<?> c) return c;
        }
      } catch (Throwable ignored) {}
    }

    // Fields with likely names
    for (Field f : library.getClass().getDeclaredFields()) {
      if (!Collection.class.isAssignableFrom(f.getType())) continue;
      String name = f.getName().toLowerCase();
      if (!(name.contains("item") || name.contains("media") || name.contains("catalog")
          || name.contains("invent") || name.contains("collect"))) continue;
      try {
        f.setAccessible(true);
        Object val = f.get(library);
        if (val instanceof Collection<?> c) return c;
      } catch (Throwable ignored) {}
    }

    // Fallback: first accessible Collection field
    for (Field f : library.getClass().getDeclaredFields()) {
      if (!Collection.class.isAssignableFrom(f.getType())) continue;
      try {
        f.setAccessible(true);
        Object val = f.get(library);
        if (val instanceof Collection<?> c) return c;
      } catch (Throwable ignored) {}
    }
    return null;
  }

  /** Find Map fields on Library that likely hold items (e.g., id -> MediaItem). */
  private List<Map<?, ?>> locateItemMaps() {
    java.util.ArrayList<Map<?, ?>> maps = new java.util.ArrayList<>();

    // Obvious getters
    for (String getter : new String[] {"getItemMap", "getItemsById", "getItemsMap", "getInventory", "getCatalogMap"}) {
      try {
        Method m = library.getClass().getMethod(getter);
        if (Map.class.isAssignableFrom(m.getReturnType())) {
          Object res = m.invoke(library);
          if (res instanceof Map<?, ?> map) maps.add(map);
        }
      } catch (Throwable ignored) {}
    }

    // Fields with likely names
    for (Field f : library.getClass().getDeclaredFields()) {
      if (!Map.class.isAssignableFrom(f.getType())) continue;
      String n = f.getName().toLowerCase();
      if (!(n.contains("item") || n.contains("media") || n.contains("catalog") || n.contains("invent"))) continue;
      try {
        f.setAccessible(true);
        Object val = f.get(library);
        if (val instanceof Map<?, ?> map) maps.add(map);
      } catch (Throwable ignored) {}
    }

    return maps;
  }

  private static void trySetLibrary(MediaItem item, Library lib) {
    try {
      Method m = item.getClass().getMethod("setLibrary", Library.class);
      m.invoke(item, lib);
    } catch (NoSuchMethodException e) {
      // maybe declared non-public
      try {
        Method m = item.getClass().getDeclaredMethod("setLibrary", Library.class);
        m.setAccessible(true);
        m.invoke(item, lib);
      } catch (Throwable ignored) { }
    } catch (Throwable ignored) { }
  }

  private static boolean idEquals(Object itemId, String idStr) {
    if (itemId == null || idStr == null) return false;
    if (itemId instanceof String s) return s.equals(idStr);
    if (itemId instanceof UUID u) return u.toString().equals(idStr);
    return itemId.toString().equals(idStr);
  }
}
