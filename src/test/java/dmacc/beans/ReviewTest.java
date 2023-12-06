package dmacc.beans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReviewTest {

	private Review review;

	@BeforeEach
	public void setUp() {
		review = new Review();
	}

	@Test
	public void testGetId() {
		Long id = 1L;
		review.setId(id);
		assertEquals(id, review.getId());
	}

	@Test
	public void testGetContent() {
		String content = "This is a review.";
		review.setContent(content);
		assertEquals(content, review.getContent());
	}

	@Test
	public void testGetRating() {
		Integer rating = 5;
		review.setRating(rating);
		assertEquals(rating, review.getRating());
	}

	@Test
	public void testGetGame() {
		Game game = new Game();
		review.setGame(game);
		assertEquals(game, review.getGame());
	}

	@Test
	public void testGetUser() {
		User user = new User();
		review.setUser(user);
		assertEquals(user, review.getUser());
	}

	@Test
	public void testEqualsSameObject() {
		assertTrue(review.equals(review));
	}

	@Test
	public void testEqualsNullObject() {
		assertFalse(review.equals(null));
	}

	@Test
	public void testEqualsDifferentClass() {
		assertFalse(review.equals(new Object()));
	}

	@Test
	public void testEqualsDifferentId() {
		Review otherReview = new Review();
		review.setId(1L);
		otherReview.setId(2L);
		assertFalse(review.equals(otherReview));
	}

	@Test
	public void testHashCode() {
		int hashCode = review.getClass().hashCode();
		assertEquals(hashCode, review.hashCode());
	}
}