package dmacc.controller;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import dmacc.beans.Game;
import dmacc.beans.Platform;
import dmacc.beans.Review;
import dmacc.repository.GameRepository;
import dmacc.repository.PlatformRepository;

public class ViewControllerTest {

    private ViewController viewController;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private PlatformRepository platformRepository;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        viewController = new ViewController(gameRepository, platformRepository);
    }

    @Test
    public void testViewPlatforms() {
        // Arrange
        List<Platform> platforms = new ArrayList<>();
        platforms.add(new Platform());
        platforms.add(new Platform());
        when(platformRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(platforms);

        // Act
        String viewName = viewController.viewPlatforms(model);

        // Assert
        assertEquals("view-platforms", viewName);
        verify(model).addAttribute("platforms", platforms);
    }

    @Test
    public void testViewGames() {
        // Arrange
        List<Game> games = new ArrayList<>();
        games.add(new Game());
        games.add(new Game());
        when(gameRepository.findAll(Sort.by(Sort.Direction.ASC, "title"))).thenReturn(games);

        // Act
        String viewName = viewController.viewGames(model);

        // Assert
        assertEquals("view-games", viewName);
        verify(model).addAttribute("games", games);
    }

    @Test
    public void testViewGame_ExistingGame() {
        // Arrange
        Long gameId = 1L;
        Game game = new Game();
        game.setGameId(gameId);
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review());
        reviews.add(new Review());
        game.setReviews(reviews);
        Optional<Game> optionalGame = Optional.of(game);
        when(gameRepository.findById(gameId)).thenReturn(optionalGame);

        // Act
        String viewName = viewController.viewGame(gameId, model);

        // Assert
        assertEquals("view-game", viewName);
        verify(model).addAttribute("game", game);
        verify(model).addAttribute("reviews", reviews);
    }

    @Test
    public void testViewGame_NonExistingGame() {
        // Arrange
        Long gameId = 1L;
        Optional<Game> optionalGame = Optional.empty();
        when(gameRepository.findById(gameId)).thenReturn(optionalGame);

        // Act
        String viewName = viewController.viewGame(gameId, model);

        // Assert
        assertEquals("redirect:/error", viewName);
    }
}