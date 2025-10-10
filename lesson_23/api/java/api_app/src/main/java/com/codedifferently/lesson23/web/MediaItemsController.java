package com.codedifferently.lesson23.web;

import com.codedifferently.lesson23.library.Librarian;
import com.codedifferently.lesson23.library.Library;
import com.codedifferently.lesson23.library.MediaItem;
import com.codedifferently.lesson23.library.search.SearchCriteria;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;
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

  @GetMapping("/")
  public ResponseEntity<Map<String, Object>> welcome() {
    Map<String, Object> response =
        Map.of(
            "message", "Welcome to the Library Management API!",
            "version", "1.0.0",
            "endpoints",
                Map.of(
                    "DELETE /items/{id}", "Delete a media item",
                    "POST /items", "Create a new media item",
                    "GET /items", "Get all media items",
                    "GET /items/{id}", "Get a specific media item"),
            "documentation",
                Map.of(
                    "note", "Built-in Swagger/OpenAPI documentation available if configured"),
            "statistics",
                Map.of(
                    "totalItems", library.search(SearchCriteria.builder().build()).size(),
                    "totalLibrarians", library.getLibrarians().size()));
    return ResponseEntity.ok(response);
  }

  @GetMapping("/items")
  public ResponseEntity<GetMediaItemsResponse> getItems() {
    Set<MediaItem> items = library.search(SearchCriteria.builder().build());
    List<MediaItemResponse> responseItems = items.stream().map(MediaItemResponse::from).toList();
    var response = GetMediaItemsResponse.builder().items(responseItems).build();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/items/{id}")
  public ResponseEntity<MediaItemResponse> getItem(@PathVariable String id) {
    Set<MediaItem> items = library.search(SearchCriteria.builder().id(id).build());
    if (items.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    MediaItem item = items.iterator().next();
    MediaItemResponse response = MediaItemResponse.from(item);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/items")
  public ResponseEntity<CreateMediaItemResponse> createItem(
      @Valid @RequestBody CreateMediaItemRequest request) {
    MediaItem mediaItem = MediaItemRequest.asMediaItem(request.getItem());
    library.addMediaItem(mediaItem, librarian);
    MediaItemResponse responseItem = MediaItemResponse.from(mediaItem);
    var response = CreateMediaItemResponse.builder().item(responseItem).build();
    return ResponseEntity.ok(response);
  }



  @DeleteMapping("/items/{id}")
  public ResponseEntity<Void> deleteItem(@PathVariable String id) {
    Set<MediaItem> items = library.search(SearchCriteria.builder().id(id).build());
    if (items.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    MediaItem item = items.iterator().next();
    library.removeMediaItem(item, librarian);
    return ResponseEntity.noContent().build();
  }
}
