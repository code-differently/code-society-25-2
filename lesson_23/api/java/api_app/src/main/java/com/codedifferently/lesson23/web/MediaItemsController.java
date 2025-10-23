package com.codedifferently.lesson23.web;

import com.codedifferently.lesson23.library.Librarian;
import com.codedifferently.lesson23.library.Library;
import com.codedifferently.lesson23.library.MediaItem;
import com.codedifferently.lesson23.library.exceptions.MediaItemCheckedOutException;
import com.codedifferently.lesson23.library.search.SearchCriteria;
import jakarta.validation.Valid;
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
    UUID itemId = UUID.fromString(id);
    Set<MediaItem> items = library.search(SearchCriteria.builder().id(itemId.toString()).build());

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
    CreateMediaItemResponse response = CreateMediaItemResponse.builder().item(responseItem).build();
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/items/{id}")
  public ResponseEntity<Void> deleteItem(@PathVariable String id) {
    UUID itemId = UUID.fromString(id);

    if (!library.hasMediaItem(itemId)) {
      return ResponseEntity.notFound().build();
    }

    try {
      library.removeMediaItem(itemId, librarian);
      return ResponseEntity.noContent().build();
    } catch (MediaItemCheckedOutException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }
}
