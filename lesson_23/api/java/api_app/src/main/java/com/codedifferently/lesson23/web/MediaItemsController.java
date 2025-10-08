package com.codedifferently.lesson23.web;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.codedifferently.lesson23.library.Librarian;
import com.codedifferently.lesson23.library.Library;
import com.codedifferently.lesson23.library.MediaItem;
import com.codedifferently.lesson23.library.exceptions.MediaItemCheckedOutException;
import com.codedifferently.lesson23.library.search.SearchCriteria;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/items") 
public class MediaItemsController {

  private final Library library;
  private final Librarian librarian;

  public MediaItemsController(Library library) throws IOException {
    this.library = Objects.requireNonNull(library, "Library cannot be null");
    this.librarian = library.getLibrarians().stream().findFirst()
        .orElseThrow(() -> new IllegalStateException("No librarians available"));
  }

  @GetMapping
  public ResponseEntity<GetMediaItemsResponse> getItems() {
    Set<MediaItem> items = library.search(SearchCriteria.builder().build());
    List<MediaItemResponse> responseItems = items.stream().map(MediaItemResponse::from).toList();
    var response = GetMediaItemsResponse.builder().items(responseItems).build();
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public CreateMediaItemResponse createItem(@Valid @RequestBody CreateMediaItemRequest request) {
    MediaItem newItem = MediaItemRequest.asMediaItem(request.getItem());
    library.addMediaItem(newItem, librarian);
    return CreateMediaItemResponse.builder()
        .item(MediaItemResponse.from(newItem))
        .build();
  }

  @GetMapping("/{id}")
  public MediaItemResponse getItemById(@PathVariable UUID id) {
    Set<MediaItem> allItems = library.search(SearchCriteria.builder().build());
    Optional<MediaItem> foundItem = allItems.stream()
        .filter(item -> item.getId().equals(id))
        .findFirst();
    
    if (foundItem.isEmpty()) {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "Media item with ID " + id + " not found."
      );
    }

    return MediaItemResponse.from(foundItem.get());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteItemById(@PathVariable UUID id) {
    Set<MediaItem> allItems = library.search(SearchCriteria.builder().build());
    Optional<MediaItem> foundItem = allItems.stream()
        .filter(item -> item.getId().equals(id))
        .findFirst();
    
    if (foundItem.isEmpty()) {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "Media item with ID " + id + " not found."
      );
    }
    
    try {
      library.removeMediaItem(id, librarian);
      return ResponseEntity.noContent().build();
    } catch (MediaItemCheckedOutException e) {
      throw new ResponseStatusException(
        HttpStatus.CONFLICT,
        "Cannot delete item: " + e.getMessage()
      );
    }
  }
}
