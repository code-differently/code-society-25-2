package com.codedifferently.lesson23.web;

import com.codedifferently.lesson23.library.Librarian;
import com.codedifferently.lesson23.library.Library;
import com.codedifferently.lesson23.library.MediaItem;
import com.codedifferently.lesson23.library.search.SearchCriteria;
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
  public ResponseEntity<MediaItemResponse> getItem(@PathVariable String id) {
    Set<MediaItem> items = library.search(SearchCriteria.builder().id(id).build());
    
    if (items.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    
    MediaItem item = items.iterator().next();
    MediaItemResponse responseItem = MediaItemResponse.from(item);
    return ResponseEntity.ok(responseItem);
  }

    @PostMapping("/items")
  public ResponseEntity<?> addItem(@RequestBody CreateMediaItemRequest request) {
    try {
      if (request.getItem() == null) {
        var errorResponse = java.util.Map.of("errors", java.util.List.of("Item is required"));
        return ResponseEntity.badRequest().body(errorResponse);
      }

      MediaItem item = MediaItemRequest.asMediaItem(request.getItem());
      library.addMediaItem(item, librarian);
      
      MediaItemResponse responseItem = MediaItemResponse.from(item);
      var response = CreateMediaItemResponse.builder().item(responseItem).build();
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      var errorResponse = java.util.Map.of("errors", java.util.List.of("Invalid item data"));
      return ResponseEntity.badRequest().body(errorResponse);
    }
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