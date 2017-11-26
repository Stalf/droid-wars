package com.droidwars.core.service;

import com.droidwars.core.entity.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuditorServiceImpl implements AuditorAware<User> {

    /**
     * Returns currently authenticated user
     *
     * @throws IllegalStateException if current user is not authenticated or not a valid system user
     */
    @Override
    public User getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth == null) || !auth.isAuthenticated()) {
            throw new IllegalStateException("No user is authenticated");
        }
        Object principal = auth.getPrincipal();
        if (!(principal instanceof User)) {
            throw new IllegalStateException("Principal is not a user");
        }

        return (User) principal;
    }
}
