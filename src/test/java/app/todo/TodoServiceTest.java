package app.todo;

import app.todo.common.SaltGenerator;
import app.todo.dao.TodoDao;
import app.todo.dao.TodoListDao;
import app.todo.dao.UserDao;
import app.todo.model.Todo;
import app.todo.model.TodoList;
import app.todo.model.User;
import app.todo.service.AuthService;
import at.favre.lib.crypto.bcrypt.BCrypt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserDao userDao;
    @Autowired
    private TodoDao todoDao;
    @Autowired
    private TodoListDao todoListDao;

    @Test
    public void whenFindById_thenReturnTodoList(){
        User user = userDao.findByUsername("ozbatman");
        TodoList todoList = new TodoList();
        todoList.setName("todoList");
        todoList.setUser(user);


        entityManager.persist(todoList);
        entityManager.flush();

        TodoList found = todoListDao.findById(todoList.getId());

        assertThat(found.getName())
                .isEqualTo(todoList.getName());
    }

    @Test
    public void whenFindById_thenReturnTodo() {
        User user = userDao.findByUsername("ozbatman");
        TodoList todoList = new TodoList();
        todoList.setName("todoList2");
        todoList.setUser(user);

        entityManager.persist(todoList);
        entityManager.flush();

        Todo todo = new Todo();
        todo.setName("todo");
        todo.setTodoStatus(TodoStatus.INCOMPLETE);
        todo.setDescription("description");
        todo.setEndDate(LocalDate.parse("2019:04:20"));
        todo.setCreatedDate(new Date());
        todo.setTodoList(todoList);

        entityManager.persist(todo);
        entityManager.flush();


        Todo found = todoDao.findById(todo.getId());

        assertThat(found.getName())
                .isEqualTo(todo.getName());
    }
}
