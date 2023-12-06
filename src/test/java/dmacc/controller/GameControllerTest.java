package dmacc.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import dmacc.beans.Game;
import dmacc.beans.Platform;
import dmacc.repository.GameRepository;
import dmacc.repository.PlatformRepository;

class GameControllerTest {

    private GameController gameController;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private PlatformRepository platformRepository;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gameController = new GameController(gameRepository, platformRepository);
    }

    @Test
    void testListGames() {
        List<Game> games = new ArrayList<>();
        games.add(new Game());
        when(gameRepository.findAll()).thenReturn(games);

        String viewName = gameController.listGames(model);

        assertEquals("list-games", viewName);
        verify(gameRepository, times(1)).findAll();
        verify(model, times(1)).addAttribute("games", games);
    }

    @Test
    void testShowAddForm() {
        List<Platform> platforms = new ArrayList<>();
        platforms.add(new Platform());
        when(platformRepository.findAll()).thenReturn(platforms);

        String viewName = gameController.showAddForm(model);

        assertEquals("add-update-game", viewName);
        verify(platformRepository, times(1)).findAll();
        verify(model, times(1)).addAttribute("platforms", platforms);
        verify(model, times(1)).addAttribute("game", new Game());
    }

    @Test
    void testAddGameWithErrors() {
        Game game = new Game();
        when(result.hasErrors()).thenReturn(true);

        String viewName = gameController.addGame(game, result);

        assertEquals("add-update-game", viewName);
        verify(gameRepository, never()).save(game);
    }

    @Test
    void testAddGameWithoutErrors() {
        Game game = new Game();
        when(result.hasErrors()).thenReturn(false);

        String viewName = gameController.addGame(game, result);

        assertEquals("redirect:/games/view/" + game.getGameId(), viewName);
        verify(gameRepository, times(1)).save(game);
    }

    @Test
    void testViewGameWithExistingGame() {
        Long gameId = 1L;
        Game game = new Game();
        Optional<Game> optionalGame = Optional.of(game);
        when(gameRepository.findById(gameId)).thenReturn(optionalGame);

        String viewName = gameController.viewGame(gameId, model);

        assertEquals("view-game", viewName);
        verify(gameRepository, times(1)).findById(gameId);
        verify(model, times(1)).addAttribute("game", game);
    }

    @Test
    void testViewGameWithNonExistingGame() {
        Long gameId = 1L;
        Optional<Game> optionalGame = Optional.empty();
        when(gameRepository.findById(gameId)).thenReturn(optionalGame);

        String viewName = gameController.viewGame(gameId, model);

        assertEquals("redirect:/error", viewName);
        verify(gameRepository, times(1)).findById(gameId);
        verify(model, never()).addAttribute("game", optionalGame.get());
    }

    @Test
    void testEditGameWithExistingGame() {
        Long gameId = 1L;
        Game game = new Game();
        Optional<Game> optionalGame = Optional.of(game);
        List<Platform> platforms = new ArrayList<>();
        platforms.add(new Platform());
        when(gameRepository.findById(gameId)).thenReturn(optionalGame);
        when(platformRepository.findAll()).thenReturn(platforms);

        String viewName = gameController.editGame(gameId, model);

        assertEquals("add-update-game", viewName);
        verify(gameRepository, times(1)).findById(gameId);
        verify(platformRepository, times(1)).findAll();
        verify(model, times(1)).addAttribute("platforms", platforms);
        verify(model, times(1)).addAttribute("game", game);
    }

    @Test
    void testEditGameWithNonExistingGame() {
        Long gameId = 1L;
        Optional<Game> optionalGame = Optional.empty();
        when(gameRepository.findById(gameId)).thenReturn(optionalGame);

        String viewName = gameController.editGame(gameId, model);

        assertEquals("redirect:/error", viewName);
        verify(gameRepository, times(1)).findById(gameId);
        verify(platformRepository, never()).findAll();
        verify(model, never()).addAttribute("platforms", anyList());
        verify(model, never()).addAttribute("game", optionalGame.get());
    }

    @Test
    void testDeleteGame() {
        Long gameId = 1L;

        String viewName = gameController.deleteGame(gameId);

        assertEquals("redirect:/games/list", viewName);
        verify(gameRepository, times(1)).deleteById(gameId);
    }
}