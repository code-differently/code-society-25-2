package com.codedifferently.lesson23.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
import lombok.AllArgsConstructor;
import lombok.Data;

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

  private MediaItem findItemById(String id) {
    Set<MediaItem> items = library.search(SearchCriteria.builder().id(id).build());
    return items.isEmpty() ? null : items.iterator().next();
  }
  
  @PostMapping("/items")
  public ResponseEntity<?> createItem(@Valid @RequestBody CreateMediaItemRequest request, BindingResult bindingResult) {
    // Handle validation errors
    if (bindingResult.hasErrors() || request.getItem() == null) {
      List<String> errors = new ArrayList<>();
      
      if (request.getItem() == null) {
        errors.add("item is required");
      } else {
        for (FieldError error : bindingResult.getFieldErrors()) {
          errors.add(error.getDefaultMessage());
        }
      }
      
      return ResponseEntity.badRequest().body(new ErrorResponse(errors));
    }
    
    try {
      // Convert request to MediaItem and add to library
      MediaItem item = MediaItemRequest.asMediaItem(request.getItem());
      library.addMediaItem(item, librarian);
      
      // Create response
      MediaItemResponse itemResponse = MediaItemResponse.from(item);
      CreateMediaItemResponse response = CreateMediaItemResponse.builder()
          .item(itemResponse)
          .build();
          
      return ResponseEntity.ok(response);
      
    } catch (Exception e) {
      List<String> errors = new ArrayList<>();
      errors.add("Failed to create item: " + e.getMessage());
      return ResponseEntity.badRequest().body(new ErrorResponse(errors));
    }
  }
  
}
