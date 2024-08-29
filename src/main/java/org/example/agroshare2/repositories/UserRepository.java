package org.example.agroshare2.repositories;

import org.example.agroshare2.dao.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, Long> {
    Optional<UserDAO> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
