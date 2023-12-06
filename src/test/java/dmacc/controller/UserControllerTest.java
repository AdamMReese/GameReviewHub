/**
 * @author Aaron Carpenter - acarpenter5@dmacc.edu
 * CIS175 - Fall 2023
 * Nov 13, 2023
 */

package dmacc.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dmacc.beans.User;
import dmacc.repository.UserRepository;
import jakarta.servlet.http.HttpSession;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserController userController;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User("testuser", "testpassword");
        userRepository.save(testUser);
    }

    @AfterEach
    public void tearDown() {
        userRepository.delete(testUser);
    }

    @Test
    void testCreateAccount() {
        String result = userController.createAccount("newuser", "newpassword");
        assertEquals("redirect:/Index.html", result);
    }

    @Test
    void testCreateAccountWithExistingUsername() {
        String result = userController.createAccount("testuser", "newpassword");
        assertEquals("redirect:/Error.html", result);
    }

    @Test
    void testLoginWithValidCredentials() {
        HttpSession session = mock(HttpSession.class); // Mocking HttpSession
        String result = userController.login("testuser", "testpassword", session, null);
        assertEquals("redirect:/Dashboard.html", result);
    }

    @Test
    void testLoginWithInvalidCredentials() {
        String result = userController.login("nonexistentuser", "password", null, null);
        assertEquals("redirect:/ErrorUserPass.html", result);
    }
}