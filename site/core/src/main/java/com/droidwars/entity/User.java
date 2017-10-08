package com.droidwars.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    // TODO Реализовать salt+MD5 и убрать поле из модельного класса
//    private String password;

}
