package com.droidwars.web.models;

import lombok.Data;
import lombok.ToString;

/**
 * POJO model for retrieving user registration data
 */
@Data
@ToString
public class UserRegistrationDTO {

    private String username;
    private String email;
    private String password;

}
