package com.droidwars.core.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Authentication request data
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationRequest {
    private String username;
    private String password;

}
