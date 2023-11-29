/**
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Oct 29, 2023
 */

package dmacc.repository;

import dmacc.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findByGame_GameId(Long gameId);

	List<Review> findByUser_UserId(Long userId);
}
