package com.codedifferently.lesson23.web;

import java.io.IOException;
import java.util.List;
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
  public ResponseEntity<CreateMediaItemResponse> addItem(@RequestBody MediaItemRequest request) {
    if (request == null) {
        return ResponseEntity.badRequest().build();
    }
    MediaItem mediaItem = MediaItemRequest.asMediaItem(request);
    library.addMediaItem(mediaItem, librarian);
    MediaItemResponse responseItem = MediaItemResponse.from(mediaItem);
    var response = CreateMediaItemResponse.builder().item(responseItem).build();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/items/{id}")
  public ResponseEntity<MediaItemResponse> getItem(@PathVariable String id) {
    UUID uuid;
    try {
      uuid = UUID.fromString(id);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    }
    Set<MediaItem> items = library.search(SearchCriteria.builder().id(uuid.toString()).build());
    MediaItem item = items.stream().findFirst().orElse(null);
    if (item == null) {
      return ResponseEntity.notFound().build();
    }
    MediaItemResponse response = MediaItemResponse.from(item);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/items/{id}")
  public ResponseEntity<Void> deleteItem(@PathVariable String id) {
    UUID uuid;
    try {
      uuid = UUID.fromString(id);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    }
    Set<MediaItem> items = library.search(SearchCriteria.builder().id(uuid.toString()).build());
    MediaItem item = items.stream().findFirst().orElse(null);
    if (item == null) {
      return ResponseEntity.notFound().build();
    }
    try {
      library.removeMediaItem(uuid, librarian);
    } catch (Exception e) {
      return ResponseEntity.status(409).build();
    }
    return ResponseEntity.noContent().build();
  }
}
