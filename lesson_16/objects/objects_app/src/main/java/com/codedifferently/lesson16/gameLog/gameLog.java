

import java.util.ArrayList;
import java.util.List;

public class gameLog{
    private String title;
    public  List<String> gameSystem;
    private double price;
    private String storeLocation;
    private int rating;


    public enum gameGenre {
        ACTION, ADVENTURE, RPG, SPORTS, PUZZLE, OTHER
    }

    private gameGenre genre;

    // Constructor
    public gameLog(String title, String gameSystem, double price, String storeLocation, int rating, gameGenre genre) {
        this.title = title;
        this.gameSystem = new ArrayList<>();
        this.gameSystem.add(gameSystem);
        this.price = price;
        this.storeLocation = storeLocation;
        this.rating = rating;
        this.genre = genre;

    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setGameSystem(String gameSystem) {
        this.gameSystem = new ArrayList<>();
        this.gameSystem.add(gameSystem);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    public void setGenre(gameGenre genre) {
        this.genre = genre;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public List<String> getGameSystem() {
        return gameSystem;
    }

    public double getPrice() {
        return price;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public int getRating() {
        return rating;
    }
    public gameGenre getGenre() {
        return genre;
    }
}
