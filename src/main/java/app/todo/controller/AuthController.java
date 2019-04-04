package app.todo.controller;

import app.todo.dto.base.BaseDto;
import app.todo.dto.base.ObjectDto;
import app.todo.model.request.SignInRequest;
import app.todo.model.request.UserRequest;
import app.todo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    AuthService authService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = {"application/json"})
    public BaseDto register(@RequestBody UserRequest userRequest) {
        return this.authService.registerUser(userRequest);
    }
    @RequestMapping(value = "/signIn", method = RequestMethod.POST, produces = {"application/json"})
    public BaseDto signIn(@RequestBody SignInRequest signInRequest) throws Exception {
        return this.authService.signIn(signInRequest);
    }
}
