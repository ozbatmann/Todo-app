package app.todo.model;

import app.todo.TodoStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="todo",schema = "todo")
public class Todo {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "todo_id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false, unique = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "todo_status", nullable = false, unique = false)
    private TodoStatus todoStatus;

    @OneToOne
    @JoinColumn(name = "dep_todo_id", nullable = true)
    private Dependent dependentTodo;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="todo_list_id", nullable = false)
    private TodoList todoList;

    @Column(name = "created_date", nullable = false, unique = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createdDate;

    @Column(name = "end_date", nullable = false, unique = false, updatable = false)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate endDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public TodoStatus getTodoStatus() {
        return todoStatus;
    }

    public void setTodoStatus(TodoStatus todoStatus) {
        this.todoStatus = todoStatus;
    }

    public TodoList getTodoList() {
        return todoList;
    }

    public void setTodoList(TodoList todoList) {
        this.todoList = todoList;
    }

    public Dependent getDependentTodo() {
        return dependentTodo;
    }

    public void setDependentTodo(Dependent dependentTodo) {
        this.dependentTodo = dependentTodo;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
