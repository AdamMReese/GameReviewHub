package dmacc.beans;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testGetGameId() {
        long gameId = 1L;
        game.setGameId(gameId);
        assertEquals(gameId, game.getGameId());
    }

    @Test
    public void testGetTitle() {
        String title = "Sample Title";
        game.setTitle(title);
        assertEquals(title, game.getTitle());
    }

    @Test
    public void testGetGenre() {
        String genre = "Sample Genre";
        game.setGenre(genre);
        assertEquals(genre, game.getGenre());
    }

    @Test
    public void testGetPlatform() {
        Platform platform = new Platform();
        game.setPlatform(platform);
        assertEquals(platform, game.getPlatform());
    }

    @Test
    public void testGetReviews() {
        List<Review> reviews = new ArrayList<>();
        game.setReviews(reviews);
        assertEquals(reviews, game.getReviews());
    }

    @Test
    public void testEquals() {
        Game otherGame = new Game();
        assertEquals(game, game);
        assertEquals(game, otherGame);
    }

    @Test
    public void testHashCode() {
        assertNotNull(game.hashCode());
    }
}