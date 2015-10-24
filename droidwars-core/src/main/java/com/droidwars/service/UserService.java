package com.droidwars.service;

import com.droidwars.entity.User;

import java.util.List;

public interface UserService {

    /**
     * @return полный список пользователей
     */
    public Iterable<User> findAll();

}
