package app.todo.service;

import app.todo.common.SaltGenerator;
import app.todo.dao.UserDao;
import app.todo.dto.base.BaseDto;
import app.todo.dto.base.ObjectDto;
import app.todo.model.User;
import app.todo.model.request.SignInRequest;
import app.todo.model.request.UserRequest;
import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Service
public class AuthService {

    @Autowired
    UserDao userDao;

    public BaseDto registerUser(UserRequest userRequest) {
        BaseDto baseDto;

        try {

            byte[] salt = SaltGenerator.generateSalt();
            byte[] password = BCrypt.withDefaults().hash(6, salt, userRequest.getPassword().getBytes(StandardCharsets.UTF_8));

            User user = new User();

            user.setFirstName(userRequest.getFirstName());
            user.setMiddleName(userRequest.getMiddleName());
            user.setLastName(userRequest.getLastName());
            user.setUsername(userRequest.getUsername());
            user.setSalt(salt);
            user.setPassword(password);

            this.userDao.save(user);

            baseDto = new BaseDto();
            baseDto.setReturnWithMessageResponseStats("User Successfully Created", true);

        } catch (Exception ex) {
            baseDto = new BaseDto();
            baseDto.setReturnWithMessageResponseStats(ex.getMessage() ,false);
            return baseDto;
        }

        return baseDto;
    }

    public BaseDto signIn(SignInRequest signInRequest) throws Exception {

        BaseDto baseDto;

        try{
            boolean userValidation = isValidUser(signInRequest.getUsername());

            User user = userDao.findByUsername(signInRequest.getUsername());
            boolean passwordValidation = isValidPassword(user, signInRequest.getPassword());

            baseDto = new ObjectDto<>(user);
            baseDto.setReturnWithMessageResponseStats("Welcome "+user.getUsername(),true);
        }catch (Exception ex){
            baseDto = new BaseDto();
            baseDto.setReturnWithMessageResponseStats(ex.getMessage(),false);
        }

        return baseDto;

    }

    private boolean isValidPassword(User user, String requestedPassword) throws Exception {

        byte[] password = BCrypt.withDefaults().hash(6, user.getSalt(), requestedPassword.getBytes(StandardCharsets.UTF_8));

        if(Arrays.equals(user.getPassword(),password)){
            return true;
        }
        throw new Exception("Wrong Password");
    }
    private boolean isValidUser(String username) throws Exception {

        User user = userDao.findByUsername(username);

        if(user != null){
            return true;
        }
        throw new Exception("Not Valid Username");
    }

}
