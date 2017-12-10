package com.droidwars.core.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This is invoked when user tries to access a secured REST resource without supplying any credentials
 * We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        String errorMessage = "Unauthorized";
        if (authException instanceof BadCredentialsException) {
            errorMessage = "Bad credentials";
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, errorMessage);
    }
}
