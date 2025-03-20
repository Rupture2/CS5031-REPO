import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

/**
 * Test file for the Users class.
 */
public class UsersTest {

    private Users user;
    private Game game1;
    private Game game2;

    /**
     * A simple dummy Game class for testing.
     */
    public static class Game {
        private int rating;
        private String title;

        public Game(String title) {
            this.title = title;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getTitle() {
            return title;
        }
    }

    @BeforeEach
    public void setUp() {
        user = new Users(1, "John Cena");
        game1 = new Game("Game One");
        game2 = new Game("Game Two");
    }

    @Test
    public void testConstructorAndGetters() {
        // Verify that the Users object is initialized correctly.
        assertEquals(1, user.getIdentifier());
        assertEquals("John Doe", user.getName());
        assertTrue(user.getRatingsGiven().isEmpty());
    }

    @Test
    public void testRateGameValidRating() {
        // Rate game1 with a valid rating.
        user.rateGame(game1, 3);
        // The game's rating should be updated.
        assertEquals(3, game1.getRating());
        // The game should be added to the user's ratingsGiven list.
        List<Game> ratedGames = user.getRatingsGiven();
        assertTrue(ratedGames.contains(game1));
        
        // Rating the same game again should update the rating but not duplicate the entry.
        int sizeBefore = ratedGames.size();
        user.rateGame(game1, 4);
        assertEquals(4, game1.getRating());
        assertEquals(sizeBefore, ratedGames.size());
    }

    @Test
    public void testRateGameInvalidRating() {
        // Attempting to rate with an invalid value should throw an exception.
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            user.rateGame(game1, 11); // Out of valid range (assumed 1-10)
        });
        assertEquals("Rating must be between 1 and 10.", exception.getMessage());
    }

    @Test
    public void testUpdateNameValid() {
        // Update the user's name with a valid new name.
        user.updateName("John Cena");
        assertEquals("John Cena", user.getName());
    }

    @Test
    public void testUpdateNameInvalid() {
        // An empty name should trigger an IllegalArgumentException.
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            user.updateName("");
        });
        assertEquals("Name cannot be null or empty.", exception.getMessage());
    }

    @Test
    public void testGetUserRating() {
        // When the user hasn't rated game2, getUserRating should return -1.
        assertEquals(-1, user.getUserRating(game2));
        
        // After rating game2, the method should return the correct rating.
        user.rateGame(game2, 4);
        assertEquals(4, user.getUserRating(game2));
    }
}
