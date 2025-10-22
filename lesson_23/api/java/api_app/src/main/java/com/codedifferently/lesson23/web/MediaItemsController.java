package com.codedifferently.lesson23.web;

import com.codedifferently.lesson23.library.Librarian;
import com.codedifferently.lesson23.library.Library;
import com.codedifferently.lesson23.library.MediaItem;
import com.codedifferently.lesson23.library.search.SearchCriteria;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping
public class MediaItemsController {

  private final Library library;
  private final Librarian librarian;

  public MediaItemsController(Library library) throws IOException {
    this.library = library;
    this.librarian = library.getLibrarians().stream().findFirst().orElseThrow();
  }

  @GetMapping("/items")
  public GetMediaItemsResponse getItems() {
    Set<MediaItem> items = library.search(SearchCriteria.builder().build());
    List<MediaItemResponse> responseItems = items.stream().map(MediaItemResponse::from).toList();
    return GetMediaItemsResponse.builder().items(responseItems).build();
  }

  @GetMapping("/items/{id}")
  public ResponseEntity<MediaItemResponse> getItem(@PathVariable String id) {
    UUID itemId = UUID.fromString(id);
    Set<MediaItem> items = library.search(SearchCriteria.builder().id(itemId.toString()).build());

    if (items.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    MediaItem item = items.iterator().next();
    return ResponseEntity.ok(MediaItemResponse.from(item));
  }

  @PostMapping("/items")
  public CreateMediaItemResponse createItem(@Valid @RequestBody CreateMediaItemRequest request) {
    MediaItem newItem = MediaItemRequest.asMediaItem(request.getItem());
    library.addMediaItem(newItem, librarian);

    MediaItemResponse itemResponse = MediaItemResponse.from(newItem);
    return CreateMediaItemResponse.builder().item(itemResponse).build();
  }

  @DeleteMapping("/items/{id}")
  public ResponseEntity<Void> deleteItem(@PathVariable String id) {
    UUID itemId = UUID.fromString(id);

    if (!library.hasMediaItem(itemId)) {
      return ResponseEntity.notFound().build();
    }

    library.removeMediaItem(itemId, librarian);
    return ResponseEntity.noContent().build();
  }
}
