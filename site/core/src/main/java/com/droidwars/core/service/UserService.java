package com.droidwars.core.service;

import com.droidwars.core.entity.User;
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
}
