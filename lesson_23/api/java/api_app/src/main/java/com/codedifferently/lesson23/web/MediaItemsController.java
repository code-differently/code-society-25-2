package com.codedifferently.lesson23.web;

import com.codedifferently.lesson23.library.Librarian;
import com.codedifferently.lesson23.library.Library;
import com.codedifferently.lesson23.library.MediaItem;
import com.codedifferently.lesson23.library.search.SearchCriteria;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public ResponseEntity<GetMediaItemsResponse> getItem(@PathVariable String id) {
    // gets teh media items that matches the id
    Set<MediaItem> items= library.search(SearchCriteria.builder().id(id).build());
    Optional<MediaItem> mediaItemById = items.stream().findFirst();
    MediaItemResponse responseItem;
    if (mediaItemById.isPresent()) {
      responseItem.from(mediaItemById.get());
    }
    
    
    


  }
}
