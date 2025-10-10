package com.codedifferently.lesson23.web;

import com.codedifferently.lesson23.library.Librarian;
import com.codedifferently.lesson23.library.Library;
import com.codedifferently.lesson23.library.MediaItem;
import com.codedifferently.lesson23.library.search.SearchCriteria;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
  private final Librarian librarian;

  public MediaItemsController(Library library) throws IOException {
    this.library = library;
    this.librarian = library.getLibrarians().stream().findFirst().orElseThrow();
  }

  @GetMapping("/items")
  public ResponseEntity<GetMediaItemsResponse> getItems() {
    Set<MediaItem> items = library.search(SearchCriteria.builder().build());
    List<MediaItemResponse> responseItems = items.stream().map(MediaItemResponse::from).toList();
    var response = GetMediaItemsResponse.builder().items(responseItems).build();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/items/{id}")
  public ResponseEntity<GetMediaItemsResponse> getItemID(@PathVariable String id) {
    Set<MediaItem> items = library.search(SearchCriteria.builder().id(id).build());
    // Optional can be an  object or null ex. a:string | null;
    // Optional here is saying if the first item ID is found grab it but if not keep it empty/null.
    Optional<MediaItem> itemsId = items.stream().findFirst();
    MediaItemResponse responseItem = null;
    if (itemsId.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    responseItem = MediaItemResponse.from(itemsId.get());
    var response = GetMediaItemsResponse.builder().item(responseItem).build();
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("items/{id}")
  public ResponseEntity<GetMediaItemsResponse> deleteItemId(@PathVariable String id) {
    Set<MediaItem> items = library.search(SearchCriteria.builder().id(id).build());
    Optional<MediaItem> itemsId = items.stream().findFirst();
    if (itemsId.isPresent()) {
      library.removeMediaItem(itemsId.get(), librarian);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("/items")
  public ResponseEntity<Map<String, MediaItemResponse>> addItem(
      @Valid @RequestBody CreateMediaItemRequest request) {
    try {
      MediaItem items = MediaItemRequest.asMediaItem(request.getItem());
      library.addMediaItem(items, librarian);
      MediaItemResponse responseItem = MediaItemResponse.from(items);
      Map<String, MediaItemResponse> response = Map.of("item", responseItem);

      return ResponseEntity.ok(response);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
