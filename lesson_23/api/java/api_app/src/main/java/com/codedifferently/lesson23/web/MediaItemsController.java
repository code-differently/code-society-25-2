package com.codedifferently.lesson23.web;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

  @GetMapping(value = "items/{id}")
  public ResponseEntity<GetMediaItemsResponse> getItem(@PathVariable String id) {
    // gets the media items that matches the id
    Set<MediaItem> items = library.search(SearchCriteria.builder().id(id).build());
    Optional<MediaItem> mediaItemById = items.stream().findFirst();
    MediaItemResponse responseItem = null;
    // if the media exists by the idea return an ok response entity otherwise return not found
    if (mediaItemById.isPresent()) {
      responseItem = MediaItemResponse.from(mediaItemById.get());
      var response = GetMediaItemsResponse.builder().item(responseItem).build();
      return ResponseEntity.ok(response);
    }
    return ResponseEntity.notFound().build();
    
  }

  @PostMapping("/items")
  public ResponseEntity<MediaItemResponse> addItem(@RequestBody MediaItemRequest item) {
    MediaItem mediaItem = MediaItemRequest.asMediaItem(item);
    if (mediaItem.getId() == null) {
      return ResponseEntity.badRequest().build();
    }
    library.addMediaItem(mediaItem, librarian);
    var response  = MediaItemResponse.from(mediaItem);
    return ResponseEntity.ok(response);


  }

}
