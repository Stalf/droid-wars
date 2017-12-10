package com.droidwars.core.service;

import com.droidwars.core.entity.User;
import com.droidwars.core.repository.UserRepository;
import com.droidwars.core.security.JwtAuthenticationRequest;
import com.droidwars.core.security.JwtAuthenticationResponse;
import com.droidwars.core.security.JwtTokenUtil;
import com.droidwars.core.security.JwtUserFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder encoder;


    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User registerUser(String username, String password) {
        return registerUser(username, password, null);
    }

    @Override
    @Transactional
    public User registerUser(String username, String password, String email) {
        // TODO insert checks
        User user = new User(username, encoder.encode(password), email);
        return userRepository.save(user);
    }

    @Override
    public JwtAuthenticationResponse authenticate(JwtAuthenticationRequest authenticationRequest) {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        log.debug("Generated token {} for user {}", token, userDetails);

        return new JwtAuthenticationResponse(token);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
