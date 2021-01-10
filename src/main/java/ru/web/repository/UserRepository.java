package ru.web.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.web.model.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

        Optional<User> findByName(String name);

}
