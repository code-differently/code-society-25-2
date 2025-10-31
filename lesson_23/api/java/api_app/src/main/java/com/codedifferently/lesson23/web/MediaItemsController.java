package com.codedifferently.lesson23.web;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
import com.codedifferently.lesson23.library.exceptions.MediaItemCheckedOutException;
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

  @PostMapping("/items")
  public ResponseEntity<CreateMediaItemResponse> createItem(
      @Valid @RequestBody CreateMediaItemRequest request) {
    try {
      MediaItem newItem = MediaItemRequest.asMediaItem(request.getItem());
      library.addMediaItem(newItem, librarian);

      MediaItemResponse responseItem = MediaItemResponse.from(newItem);
      var response = CreateMediaItemResponse.builder().item(responseItem).build();
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping("/items/{id}")
  public ResponseEntity<MediaItemResponse> getItem(@PathVariable UUID id) {
    if (!library.hasMediaItem(id)) {
      return ResponseEntity.notFound().build();
    }

    // Search for the specific item by ID
    Set<MediaItem> items = library.search(SearchCriteria.builder().id(id.toString()).build());
    MediaItem item = items.stream().findFirst().orElse(null);

    if (item == null) {
      return ResponseEntity.notFound().build();
    }

    MediaItemResponse response = MediaItemResponse.from(item);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/items/{id}")
  public ResponseEntity<Void> deleteItem(@PathVariable UUID id) {
    try {
      if (!library.hasMediaItem(id)) {
        return ResponseEntity.notFound().build();
      }

      library.removeMediaItem(id, librarian);
      return ResponseEntity.noContent().build();
    } catch (MediaItemCheckedOutException e) {
      // Item is checked out and cannot be deleted
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }
}
