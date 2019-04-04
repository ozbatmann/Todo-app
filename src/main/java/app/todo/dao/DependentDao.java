package app.todo.dao;

import app.todo.model.Dependent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.UUID;

public interface DependentDao extends JpaRepository<Dependent, Serializable> {

    Dependent findById(UUID dependentId);
}
