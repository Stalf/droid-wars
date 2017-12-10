package com.droidwars.core.service;

import com.droidwars.core.entity.User;
import com.droidwars.core.security.JwtAuthenticationRequest;
import com.droidwars.core.security.JwtAuthenticationResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    /**
     * @return complete user list
     */
    List<User> findAll();

    /**
     * @return user by id
     */
    User findOne(Long id);

    /**
     * Registers a new user without email address
     * @param username will be checked for uniqueness
     * @param password will be checked for strength and consistency
     * @return newly persisted User object
     */
    User registerUser(String username, String password);

    /**
     * Registers a new user
     * @param username will be checked for uniqueness
     * @param password will be checked for strength and consistency
     * @param email optional, will be checked for uniqueness if not {@code null}
     * @return newly persisted User object
     */
    User registerUser(String username, String password, String email);

    /**
     * Performs users authentication using JWT
     * @param authenticationRequest JWT authentication request
     * @return JWT authentication response containing user token
     */
    JwtAuthenticationResponse authenticate(JwtAuthenticationRequest authenticationRequest);
}
