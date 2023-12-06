package dmacc.beans;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlatformTest {

    private Platform platform;
    private List<Game> games;

    @BeforeEach
    public void setUp() {
        platform = new Platform();
        platform.setPlatformId(1L);
        platform.setName("Xbox");

        games = new ArrayList<>();
        Game game1 = new Game();
        game1.setGameId(1L);
        game1.setTitle("Halo");
        game1.setPlatform(platform);

        Game game2 = new Game();
        game2.setGameId(2L);
        game2.setTitle("Gears of War");
        game2.setPlatform(platform);

        games.add(game1);
        games.add(game2);
        platform.setGames(games);
    }

    @Test
    public void testGetPlatformId() {
        Long expectedId = 1L;
        Long actualId = platform.getPlatformId();
        Assertions.assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetName() {
        String expectedName = "Xbox";
        String actualName = platform.getName();
        Assertions.assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetGames() {
        List<Game> expectedGames = games;
        List<Game> actualGames = platform.getGames();
        Assertions.assertEquals(expectedGames, actualGames);
    }

    @Test
    public void testToString() {
        String expectedToString = "Platform(platformId=1, name=Xbox)";
        String actualToString = platform.toString();
        Assertions.assertEquals(expectedToString, actualToString);
    }
}