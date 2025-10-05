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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
// Import your DTOs, e.g.:
import com.codedifferently.lesson23.web.MediaItemRequest;
import com.codedifferently.lesson23.web.GetMediaItemsResponse;
import com.codedifferently.lesson23.web.MediaItemResponse;

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
  public ResponseEntity<MediaItemResponse> addItem(@RequestBody MediaItemRequest request) {
    MediaItem item = librarian.addNewItem(request.getTitle(), request.getType());
    var response = MediaItemResponse.builder().item(MediaItemResponse.from(item)).build();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/items/{id}")
  public ResponseEntity<MediaItemResponse> getItem(@PathVariable String id) {
    if (library.getItem(id) == null) {
      return ResponseEntity.notFound().build();
    }
      MediaItem item = library.getItem(id);
      var response = GetMediaItemsResponse.builder().item(MediaItemResponse.from(item)).build();
      return ResponseEntity.ok(response);
  }

  @DeleteMapping("/items/{id}")
  public ResponseEntity<Void> deleteItem(@PathVariable String id) {
    if (library.getItem(id) == null) {
      return ResponseEntity.notFound().build();
    }
    librarian.removeItem(id);
    return ResponseEntity.noContent().build();
  }
}
