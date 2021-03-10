package nl.hu.cisq1.lingo.security.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringUserRespository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
