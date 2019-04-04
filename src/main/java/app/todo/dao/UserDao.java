package app.todo.dao;

import app.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.UUID;

public interface UserDao extends JpaRepository<User, Serializable> {

    User findByUsername(String username);
    User findById(UUID userId);
}
