/**
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Oct 29, 2023
 */

package com.gamereviewhub.gamereviewhub;

import com.gamereviewhub.gamereviewhub.controller.ReviewController;
import com.gamereviewhub.gamereviewhub.model.Game;
import com.gamereviewhub.gamereviewhub.model.Review;
import com.gamereviewhub.gamereviewhub.repository.GameRepository;
import com.gamereviewhub.gamereviewhub.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReviewControllerTest {

	@MockBean
	private ReviewRepository reviewRepository;

	@MockBean
	private GameRepository gameRepository;

	@InjectMocks
	private ReviewController reviewController;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
	}

	@Test
	public void testListReviewsByGame() throws Exception {
		mockMvc.perform(get("/reviews/list/1")).andExpect(status().isOk());
	}

	@Test
	public void testListReviewsByUser() throws Exception {
		mockMvc.perform(get("/reviews/list/user/1")).andExpect(status().isOk());
	}

	@Test
    public void testNewReview() throws Exception {
        when(gameRepository.findById(1L)).thenReturn(Optional.of(new Game()));
        mockMvc.perform(get("/reviews/new/1"))
                .andExpect(status().isOk());
    }

	@Test
    public void testEditReview() throws Exception {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(new Review()));
        mockMvc.perform(get("/reviews/edit/1"))
                .andExpect(status().isOk());
    }

	@Test
	public void testSaveReview() throws Exception {
		mockMvc.perform(post("/reviews/save")).andExpect(status().is3xxRedirection());
	}

	@Test
    public void testDeleteReview() throws Exception {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(new Review()));
        mockMvc.perform(get("/reviews/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/reviews/list"));  // gameId will be null in this test
    }
}