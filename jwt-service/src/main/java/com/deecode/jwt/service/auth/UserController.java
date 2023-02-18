package com.deecode.jwt.service.auth;

import com.deecode.jwt.service.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService service;

    @GetMapping("/getUser/{email}")
    public User getUserByEmail(@PathVariable("email") String email){
        return service.getUserByEmail(email);
    }
}
