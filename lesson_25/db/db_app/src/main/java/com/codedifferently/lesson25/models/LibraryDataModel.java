package com.codedifferently.lesson25.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codedifferently.lesson25.library.user.LibraryUserModel;

/**
 * Data model used by loaders/factory.
 * Includes legacy fields expected by CSV/Factory code + Lesson 25 users.
 *
 * Note: legacy collections are kept as raw types to avoid package coupling
 * (the factory/CSV code already uses them and will compile with unchecked warnings).
 */
public class LibraryDataModel {

  // ===== Legacy fields expected by LibraryCsvDataLoader / LibraryFactory =====
  // (CSV loader assigns to these directly: model.mediaItems, model.guests)
  public List mediaItems = new ArrayList();          // e.g., List<MediaItem>
  public List guests = new ArrayList();              // e.g., List<LibraryGuest>
  public Map checkoutsByEmail = new HashMap();       // e.g., Map<String, List<CheckoutModel>>

  // Legacy getters used by LibraryFactory
  public List getMediaItems() { return mediaItems; }
  public List getGuests() { return guests; }
  public Map getCheckoutsByEmail() { return checkoutsByEmail; }

  // ===== Lesson 25: Users loaded from DB =====
  private List<LibraryUserModel> users = new ArrayList<>();

  public List<LibraryUserModel> getUsers() { return users; }
  public void setUsers(List<LibraryUserModel> users) { this.users = users; }
}
