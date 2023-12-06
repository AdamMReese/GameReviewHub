package dmacc.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import dmacc.beans.Review;

@DataJpaTest
public class ReviewRepositoryTest {

	@Autowired
	private ReviewRepository reviewRepository;

	@Test
	public void testFindByGameId() {
		// Add some mock data and test
		Review review1 = new Review();
		review1.setId(1L);
		reviewRepository.save(review1);

		Review review2 = new Review();
		review2.setId(1L);
		reviewRepository.save(review2);

		List<Review> reviews = reviewRepository.findByGame_GameId(1L);
		assertEquals(2, reviews.size());
	}
}
