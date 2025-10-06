package com.codedifferently.lesson23.web;

import com.codedifferently.lesson23.web.GetMediaItemsResponse;
import com.codedifferently.lesson23.web.MediaItemResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.codedifferently.lesson23.library.Librarian;
import com.codedifferently.lesson23.library.Library;
import com.codedifferently.lesson23.library.MediaItem;
import com.codedifferently.lesson23.library.search.SearchCriteria;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.Objects;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@CrossOrigin
@RequestMapping("/api/media-items")
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
  public MediaItemResponse createItem(@RequestBody MediaItem newItem) {
    MediaItem createdItem = librarian.add(newItem);
    return MediaItemResponse.from(createdItem);
  }

  @GetMapping("/{id}")
  public MediaItemResponse getItemById(@PathVariable Long id) {
    MediaItem foundItem = librarian.retrieve(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Media item with ID " + id + " not found."
        ));
    return MediaItemResponse.from(foundItem);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteItemById(@PathVariable Long id) {
    librarian.remove(id);
    return ResponseEntity.noContent().build();
  }
}