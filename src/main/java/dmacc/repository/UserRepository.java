/**
 * @author Aaron Carpenter - acarpenter5@dmacc.edu
 * CIS175 - Fall 2023
 * Nov 13, 2023
 */

package dmacc.repository;

import dmacc.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByUsername(String username);
	User findByUsernameAndPassword(String username, String password);
	User findByUsername(String username);
}
