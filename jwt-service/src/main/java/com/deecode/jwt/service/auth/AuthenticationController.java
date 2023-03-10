package com.deecode.jwt.service.auth;

import com.deecode.jwt.service.exception.InvalidDataException;
import com.deecode.jwt.service.exception.UserAlreadyExistException;
import com.deecode.jwt.service.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) throws UserAlreadyExistException, InvalidDataException {
    return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws UsernameNotFoundException {
        return ResponseEntity.ok(service.authenticate(request));
    }



}
