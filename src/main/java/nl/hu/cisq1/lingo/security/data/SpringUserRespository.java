package nl.hu.cisq1.lingo.security.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringUserRespository extends JpaRepository<User, Long> {
}
