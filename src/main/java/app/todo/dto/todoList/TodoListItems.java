package app.todo.dto.todoList;

import app.todo.model.Todo;
import app.todo.model.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

public class TodoListItems {

    private UUID todoListId;

    private String name;

    private User userId;

    public TodoListItems(UUID todoListId, String name, User userId) {
        this.todoListId = todoListId;
        this.name = name;
        this.userId = userId;
    }

    public UUID getTodoListId() {
        return todoListId;
    }

    public void setTodoListId(UUID todoListId) {
        this.todoListId = todoListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
