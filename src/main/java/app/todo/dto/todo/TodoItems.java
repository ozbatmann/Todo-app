package app.todo.dto.todo;

import app.todo.TodoStatus;
import app.todo.model.Dependent;
import java.util.Date;
import java.util.UUID;

public class TodoItems {

    private UUID todoId;

    private String name;

    private String description;

    private TodoStatus todoStatus;

    private Date createdDate;

    private Date endDate;

    private Dependent dependentTodo;

    public TodoItems(UUID todoId, String name, String description, TodoStatus todoStatus, Date createdDate, Date endDate, Dependent dependentTodo) {
        this.todoId = todoId;
        this.name = name;
        this.description = description;
        this.todoStatus = todoStatus;
        this.createdDate = createdDate;
        this.endDate = endDate;
        this.dependentTodo = dependentTodo;
    }

    public UUID getTodoId() {
        return todoId;
    }

    public void setTodoListId(UUID todoId) {
        this.todoId = todoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TodoStatus getTodoStatus() {
        return todoStatus;
    }

    public void setTodoStatus(TodoStatus todoStatus) {
        this.todoStatus = todoStatus;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Dependent getDependentTodo() {
        return dependentTodo;
    }

    public void setDependentTodo(Dependent dependentTodo) {
        this.dependentTodo = dependentTodo;
    }
}
