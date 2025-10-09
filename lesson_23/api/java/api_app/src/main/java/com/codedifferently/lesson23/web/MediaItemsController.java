package com.codedifferently.lesson23.web;
import java.util.Collections;
import java.util.Map;
import com.codedifferently.lesson23.library.Librarian;
import java.util.UUID;

import com.codedifferently.lesson23.library.Library;
import com.codedifferently.lesson23.library.MediaItem;
import com.codedifferently.lesson23.library.search.SearchCriteria;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Set;
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

  public MediaItemsController(Library library) throws IOException {
    this.library = library;
  }

  @GetMapping("/items")
  public ResponseEntity<GetMediaItemsResponse> getItems() {
    Set<MediaItem> items = library.search(SearchCriteria.builder().build());
    List<MediaItemResponse> responseItems = items.stream().map(MediaItemResponse::from).toList();
    var response = GetMediaItemsResponse.builder().items(responseItems).build();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/items/{id}")
  public ResponseEntity<Map<String, MediaItemResponse>> getItem(@PathVariable String id) {
    Set<MediaItem> items = library.search(SearchCriteria.builder().id(id).build());
    if (items.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    MediaItem item = items.iterator().next();
    var body = Collections.singletonMap("item", MediaItemResponse.from(item));
    return ResponseEntity.ok(body);
  }

  @PostMapping("/items")
  public ResponseEntity<Map<String, MediaItemResponse>> addItem(@Valid @RequestBody AddMediaItemRequest request) {
    MediaItem item = request.getItem().toDomain();
    library.addMediaItem(item, new Librarian("System", "system@library.local"));
    var body = Collections.singletonMap("item", MediaItemResponse.from(item));
    return ResponseEntity.ok(body);
  }

  @DeleteMapping("/items/{id}")
  public ResponseEntity<Void> deleteItem(@PathVariable String id) {
    Set<MediaItem> items = library.search(SearchCriteria.builder().id(id).build());
    if (items.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    library.removeMediaItem(UUID.fromString(id), new Librarian("System", "system@library.local"));
    return ResponseEntity.noContent().build();
  }
}
