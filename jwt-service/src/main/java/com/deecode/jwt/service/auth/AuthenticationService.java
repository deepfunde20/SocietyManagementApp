package com.deecode.jwt.service.auth;

import com.deecode.jwt.service.config.JwtService;
import com.deecode.jwt.service.exception.InvalidDataException;
import com.deecode.jwt.service.exception.UserAlreadyExistException;
import com.deecode.jwt.service.repo.UserRepository;
import com.deecode.jwt.service.user.Role;
import com.deecode.jwt.service.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) throws UserAlreadyExistException, InvalidDataException {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        if(request.getFirstname().isEmpty()){
            throw new InvalidDataException("First name cannot be empty");
        }
        if(request.getEmail().isEmpty()){
            throw new InvalidDataException("Email cannot be empty");
        }
        if(request.getPassword().isEmpty()){
            throw new InvalidDataException("Password cannot be empty");
        }

       Optional<User> tempUser = repository.findByEmail(request.getEmail());
       if(tempUser.isEmpty()){
           repository.save(user);
           var jwtToken = jwtService.generateToken(user);
           return AuthenticationResponse.builder().token(jwtToken).build();
       }else{
           throw new UserAlreadyExistException("User with this email already exist");
       }


    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }


    public User getUserByEmail(String email) {
      return  repository.findByEmail(email).get();
    }
}
