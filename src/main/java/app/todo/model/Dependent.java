package app.todo.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="dependent",schema = "todo")
public class Dependent {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "dep_id", nullable = false, unique = true)
    private UUID id;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "dep_todo_id", nullable = false)
    private Todo todo;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Todo getDependentTodo() {
        return todo;
    }

    public void setDependentTodo(Todo todo) {
        this.todo = todo;
    }
}
