package com.droidwars.web.controller;

import com.droidwars.core.entity.User;
import com.droidwars.core.security.JwtAuthenticationRequest;
import com.droidwars.core.security.JwtAuthenticationResponse;
import com.droidwars.core.service.UserService;
import com.droidwars.web.models.UserRegistrationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuditorAware<User> auditorService;

    @GetMapping("/api/users")
    public Iterable<User> getUserList() {
        return userService.findAll();
    }

    @PostMapping("/api/register")
    public User createUser(@RequestBody UserRegistrationDTO userRegistration) {
        User user = userService.registerUser(userRegistration.getUsername(), userRegistration.getPassword(), userRegistration.getEmail());
        log.debug("Registered new user {}", user.toString());
        return user;
    }

    @GetMapping("/api/current-user")
    public Principal getCurrentUser(Principal user) {
        return user;
    }

    @PostMapping("/api/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        // Authenticate
        JwtAuthenticationResponse authenticationResponse = userService.authenticate(authenticationRequest);

        // Return the token
        return ResponseEntity.ok(authenticationResponse);
    }
}
