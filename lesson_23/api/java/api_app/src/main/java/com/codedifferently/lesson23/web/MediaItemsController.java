package com.codedifferently.lesson23.web;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codedifferently.lesson23.library.Librarian;
import com.codedifferently.lesson23.library.Library;
import com.codedifferently.lesson23.library.MediaItem;
import com.codedifferently.lesson23.library.search.SearchCriteria;

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
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    MediaItem item = items.iterator().next();
    MediaItemResponse response = MediaItemResponse.from(item);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/items")
  public ResponseEntity<CreateMediaItemResponse> addItem(@RequestBody CreateMediaItemRequest request) {
    if (request.getItem() == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    MediaItem item = MediaItemRequest.asMediaItem(request.getItem());
    library.addMediaItem(item, librarian);
    
    MediaItemResponse itemResponse = MediaItemResponse.from(item);
    var response = CreateMediaItemResponse.builder()
        .item(itemResponse)
        .build();
    
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/items/{id}")
  public ResponseEntity<Void> deleteItem(@PathVariable String id) {
    try {
      // Convert String id to UUID
      java.util.UUID uuid = java.util.UUID.fromString(id);
      
      // Check if item exists before trying to remove
      if (!library.hasMediaItem(uuid)) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
      
      // Remove the item
      library.removeMediaItem(uuid, librarian);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      
    } catch (IllegalArgumentException e) {
      // Invalid UUID format
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    } catch (Exception e) {
      // Handle other exceptions (like MediaItemCheckedOutException)
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }
}
