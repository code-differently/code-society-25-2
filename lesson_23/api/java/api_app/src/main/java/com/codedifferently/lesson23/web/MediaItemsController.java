package com.codedifferently.lesson23.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

  @PostMapping("/items")
  public ResponseEntity<?> createItem(@RequestBody CreateMediaItemRequest request) {
    if (request == null || request.getItem() == null) {
      return ResponseEntity.badRequest().body(Map.of("errors", List.of("item is required")));
    }
    try {
      MediaItem newItem = MediaItemRequest.asMediaItem(request.getItem());
      library.addMediaItem(newItem, librarian);
      
      MediaItemResponse itemResponse = MediaItemResponse.from(newItem);
      CreateMediaItemResponse response = CreateMediaItemResponse.builder()
        .item(itemResponse)
        .build();
      return ResponseEntity.ok(response);
      
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(Map.of("errors", List.of(e.getMessage())));
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(Map.of("errors", List.of("An unexpected error occurred")));
    }
  }
}
