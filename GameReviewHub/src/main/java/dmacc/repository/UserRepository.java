package dmacc.repository;

import dmacc.model.UsersTable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UsersTable, Long> {
	boolean existsByUsername(String username);
	UsersTable findByUsernameAndPassword(String username, String password);
}
