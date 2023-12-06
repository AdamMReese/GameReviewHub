package dmacc.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dmacc.beans.Game;

@SpringBootTest
class GameRepositoryTest {
    @Autowired
    private GameRepository gameRepository;

    private Game testGame;

    @BeforeEach
    public void setUp() {
        testGame = new Game();
        gameRepository.save(testGame);
    }

    @AfterEach
    public void tearDown() {
        gameRepository.delete(testGame);
    }

    @Test
    void testFindById() {
        Game foundGame = gameRepository.findById(testGame.getGameId()).orElse(null);
        assertNotNull(foundGame);
        assertEquals("Test Game", foundGame.getTitle());
    }

    @Test
    void testSave() {
        Game newGame = new Game();
        gameRepository.save(newGame);
        assertNotNull(newGame.getGameId());

        Game foundGame = gameRepository.findById(newGame.getGameId()).orElse(null);
        assertNotNull(foundGame);
        assertEquals("New Game", foundGame.getTitle());
    }

    @Test
    void testDelete() {
        gameRepository.delete(testGame);
        assertFalse(gameRepository.existsById(testGame.getGameId()));
    }
}