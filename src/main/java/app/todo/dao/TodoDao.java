package app.todo.dao;

import app.todo.TodoStatus;
import app.todo.model.Todo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public interface TodoDao extends JpaRepository<Todo, Serializable> {

    Todo findById(UUID todoId);


    @Query("SELECT t FROM Todo t WHERE " +
            "t.todoList.id = :todoListId AND " +
            "t.todoStatus = :todoStatus AND " +
            "t.name LIKE %:name%")
    List<Todo> findByAndSort(UUID todoListId,String name, TodoStatus todoStatus, Sort sort);

    @Query("SELECT t FROM Todo t WHERE " +
            "t.todoList.id = :todoListId AND " +
            "t.name LIKE %:name%")
    List<Todo> findByNameAndSort(UUID todoListId,String name, Sort sort);

    @Query("SELECT t FROM Todo t WHERE " +
            "t.todoList.id = :todoListId AND " +
            "t.todoStatus = :todoStatus")
    List<Todo> findByTodoStatusAndSort(UUID todoListId,TodoStatus todoStatus, Sort sort);

    @Query("SELECT t FROM Todo t WHERE " +
            "t.todoList.id = :todoListId")
    List<Todo> findAllByTodoListIdAndSort(UUID todoListId, Sort sort);

    List<Todo> findAllByTodoListIdAndDependentTodoIsNullAndIdIsNotAndTodoStatusEquals(UUID todoListId,UUID todoId, TodoStatus todoStatus);
    List<Todo> findAllByTodoListIdAndDependentTodoIsNullAndTodoStatusEquals(UUID todoListId,TodoStatus todoStatus);

}