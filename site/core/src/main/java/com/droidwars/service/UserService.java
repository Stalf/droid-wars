package com.droidwars.service;

import com.droidwars.entity.User;

public interface UserService {

    /**
     * @return полный список пользователей
     */
    public Iterable<User> findAll();

}
