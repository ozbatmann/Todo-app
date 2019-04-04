package app.todo.model.request;

import java.util.UUID;

public class TodoRequest {

    private String id;

    private String name;

    private String description;

    private String todoStatus;

    private String todoListId;

    private String dependentTodo;

    private String endDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTodoStatus() {
        return todoStatus;
    }

    public void setTodoStatus(String todoStatus) {
        this.todoStatus = todoStatus;
    }

    public String getTodoListId() {
        return todoListId;
    }

    public void setTodoListId(String todoListId) {
        this.todoListId = todoListId;
    }

    public String getDependentTodo() {
        return dependentTodo;
    }

    public void setDependentTodo(String dependentTodo) {
        this.dependentTodo = dependentTodo;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
