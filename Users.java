import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a user in the game repository system.
 * Each user has a unique identifier, a name, and a list of games they have rated.
 */
public class Users {

    private int identifier;
    private String name;
    private ArrayList<Game> ratingsGiven;

    /**
     * Constructs a new user with the specified identifier and name.
     *
     * @param identifier the unique identifier for this user
     * @param name the name of the user
     */
    public Users(int identifier, String name) {
        this.identifier = identifier;
        this.name = name;
        this.ratingsGiven = new ArrayList<>();
    }

    /**
     * Rates a given game with the specified rating value.
     * If the user has not rated this game before, the game is
     * added to the user's {@code ratingsGiven} list.
     *
     * @param specificGame the game to rate
     * @param rating       the rating value (Will keep this in 1-10)
     * @throws IllegalArgumentException if the rating is out of an acceptable range
     */
    public void rateGame(Game specificGame, int rating) {
        // Example validation: rating must be between 1 and 10
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }

        specificGame.setRating(rating);

        // Add the game to the list if not already present
        if (!ratingsGiven.contains(specificGame)) {
            ratingsGiven.add(specificGame);
        }
    }

    /**
     * Updates the user's name.
     *
     * @param newName the new name to set
     * @throws IllegalArgumentException if the new name is null or empty
     */
    public void updateName(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = newName;
    }

    /**
     * Retrieves the user's rating for a specific game.
     *
     * @param game the game to retrieve the rating for
     * @return the rating value for the game, or -1 if the user has not rated it
     */
    public int getUserRating(Game game) {
        if (!ratingsGiven.contains(game)) {
            return -1; // Indicates that the user hasn't rated this game
        }
        return game.getRating();
    }


    // Getters and Setters below


    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public List<Game> getRatingsGiven() {
        return ratingsGiven;
    }

    public void setRatingsGiven(ArrayList<Game> ratingsGiven) {
        this.ratingsGiven = ratingsGiven;
    }



    // Object Overrides


    /**
     * Returns a string representation of the user, including
     * the identifier, name, and the number of games rated.
     * Have added this as an extra functionality.
     */
    @Override
    public String toString() {
        return "Users{" +
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                ", ratedGamesCount=" + ratingsGiven.size() +
                '}';
    }

}
