package app.todo.dao;

import app.todo.model.TodoList;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public interface TodoListDao extends JpaRepository<TodoList, Serializable> {

    List<TodoList> findAllByUserId(UUID userId, Sort sort);
    TodoList findById(UUID todoListId);
}
