package dmacc.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import dmacc.beans.Platform;
import dmacc.repository.PlatformRepository;

class PlatformControllerTest {

    private PlatformController platformController;

    @Mock
    private PlatformRepository platformRepository;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        platformController = new PlatformController(platformRepository);
    }

    @Test
    void testListPlatforms() {
        // Arrange
        List<Platform> platforms = new ArrayList<>();
        platforms.add(new Platform());
        platforms.add(new Platform());
        when(platformRepository.findAll()).thenReturn(platforms);

        // Act
        String viewName = platformController.listPlatforms(model);

        // Assert
        assertEquals("list-platforms", viewName);
        verify(model, times(1)).addAttribute("platforms", platforms);
    }

    @Test
    void testShowAddForm() {
        // Act
        String viewName = platformController.showAddForm(model);

        // Assert
        assertEquals("add-update-platform", viewName);
        verify(model, times(1)).addAttribute(eq("platform"), any(Platform.class));
    }

    // Add more tests for other methods in the PlatformController class

}