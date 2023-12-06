/**
 * @author Aaron Carpenter - acarpenter5@dmacc.edu
 * CIS175 - Fall 2023
 * Nov 13, 2023
 */

package dmacc.beans;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import dmacc.repository.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddUser() {
        User user = new User("testuser", "testpassword");
        userRepository.save(user);
        User savedUser = userRepository.findByUsername("testuser");
        assertEquals("testpassword", savedUser.getPassword());
    }

    @Test
    public void testRemoveUser() {
        User user = new User("testuser", "testpassword");
        userRepository.save(user);
        userRepository.delete(user);
        assertTrue(userRepository.findByUsername("testuser") == null);
    }
}

