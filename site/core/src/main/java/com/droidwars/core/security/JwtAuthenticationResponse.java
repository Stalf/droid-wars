package com.droidwars.core.security;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Authentication response data
 */
@Data
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private final String token;
}
