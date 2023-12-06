package dmacc.repository;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import dmacc.beans.Platform;

@DataJpaTest
@ActiveProfiles("test")
public class PlatformRepositoryTest {

    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    private TestEntityManager entityManager;

    // Define a constructor in the Platform class that accepts a String parameter
    public void Platform(String name) {
    }

    @Test
    public void testFindPlatformById() {
        // Create a new Platform object
        Platform platform = new Platform();

        // Save the Platform object to the repository
        entityManager.persist(platform);
        entityManager.flush();

        // Retrieve the Platform object by its ID
        Optional<Platform> optionalPlatform = platformRepository.findById(platform.getId());

        // Verify that the Platform object is retrieved successfully
        Assertions.assertTrue(optionalPlatform.isPresent());
        Assertions.assertEquals("Xbox", optionalPlatform.get().getName());
    }

    @Test
    public void testDeletePlatform() {
        // Create a new Platform object
        Platform platform = new Platform();

        // Save the Platform object to the repository
        entityManager.persist(platform);
        entityManager.flush();

        // Delete the Platform object from the repository
        platformRepository.delete(platform);

        // Verify that the Platform object is deleted successfully
        Optional<Platform> optionalPlatform = platformRepository.findById(platform.getId());
        Assertions.assertFalse(optionalPlatform.isPresent());
    }
}