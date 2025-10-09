package com.codedifferently.lesson23.web;
import java.util.UUID;

import com.codedifferently.lesson23.library.MediaItem;
import com.codedifferently.lesson23.library.Book;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import jakarta.validation.constraints.NotNull;

@Value
@Builder
public class AddMediaItemRequest {
  @NotNull
  Item item;

  @Value
  @Builder
  public static class Item {
    String id;
    String type;
    String title;
    String isbn;
    List<String> authors;
    Integer pages;

    public MediaItem toDomain() {
      int p = pages == null ? 0 : pages;
      return new Book(UUID.fromString(id), title, isbn, authors, p);
    }
  }

  public Item getItem() { return item; }
}
