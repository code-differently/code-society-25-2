import java.util.ArrayList;
import java.util.List;

public class GameLogFunctions {
    private static List<Object> gameSystem = null;
    private static final Object system = new Object(); // 

    private int rating; 

    public static void addGameSystem() {
        if(gameSystem == null) {
            gameSystem = new ArrayList<>();
        }
        gameSystem.add(system);
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
    }

    public int getRating() {
        if(rating >=8){
            System.out.println("This game is highly rated! " + rating);
        } else if (rating >=5) {
            System.out.println("This game has an average rating. " + rating);
        } else {
            System.out.println("This game has a low rating. " + rating);
        }
        return rating;
    }

    public int getSystemCount() {
        return gameSystem.size();
    }
}