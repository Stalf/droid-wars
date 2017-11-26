package com.droidwars.core.service;

import com.droidwars.core.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    /**
     * @return complete user list
     */
    Iterable<User> findAll();

}
