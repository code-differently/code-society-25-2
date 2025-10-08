package com.codedifferently.lesson23.web;
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

import com.codedifferently.lesson23.library.Librarian;
import com.codedifferently.lesson23.library.Library;
import com.codedifferently.lesson23.library.MediaItem;
import com.codedifferently.lesson23.library.search.SearchCriteria;

import jakarta.validation.Valid;

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
  public ResponseEntity<CreateMediaItemResponse> getItemById(@PathVariable String id) {
    Set<MediaItem> items = library.search(SearchCriteria.builder().id(id).build());
    if (items.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    MediaItem item = items.iterator().next();
    var response = CreateMediaItemResponse.builder()
        .item(MediaItemResponse.from(item))
        .build();
    return ResponseEntity.ok(response);
  }

  @PostMapping("/items")
  public ResponseEntity<CreateMediaItemResponse> addItem(@Valid @RequestBody CreateMediaItemRequest request) {
    try {
      MediaItem mediaItem = MediaItemRequest.asMediaItem(request.getItem());
      library.addMediaItem(mediaItem, librarian);
      CreateMediaItemResponse response = CreateMediaItemResponse.builder()
          .item(MediaItemResponse.from(mediaItem))
          .build();
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @DeleteMapping("/items/{id}")
  public ResponseEntity<Void> deleteItem(@PathVariable String id) {
    try {
      java.util.UUID uuid = java.util.UUID.fromString(id);
      library.removeMediaItem(uuid, librarian);
      return ResponseEntity.noContent().build();
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }
}