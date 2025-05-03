package psmor.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psmor.jpa.srtuct.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String name); // Поиск по имени

}
