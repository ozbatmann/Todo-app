package app.todo;

import app.todo.common.SaltGenerator;
import app.todo.dao.UserDao;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class AuthServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
   private UserDao userDao;

    @Test
    public void whenFindByUsername_thenReturnUser() {
        User user = new User();
        user.setFirstName("firstName");
        user.setMiddleName("middleName");
        user.setLastName("lastName");
        user.setUsername("username");
        byte[] salt = SaltGenerator.generateSalt();
        String tempPassword = "password";
        user.setSalt(salt);
        byte[] password = BCrypt.withDefaults().hash(6, salt, tempPassword.getBytes(StandardCharsets.UTF_8));
        user.setPassword(password);

        entityManager.persist(user);
        entityManager.flush();

        User found = userDao.findByUsername(user.getUsername());

        assertThat(found.getUsername())
                .isEqualTo(user.getUsername());
    }
}
