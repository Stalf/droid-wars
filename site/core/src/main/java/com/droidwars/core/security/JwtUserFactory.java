package com.droidwars.core.security;

import com.droidwars.core.entity.Role;
import com.droidwars.core.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Factory for building JWT instances
 */
public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
            user.getId(),
            user.getUsername(),
            user.getPass(),
            mapToGrantedAuthorities(user.getRoles()),
            user.isEnabled(),
            Date.from(user.getPasswordResetDate().atZone(ZoneId.systemDefault()).toInstant())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Set<Role> authorities) {
        return authorities.stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getCode().name()))
            .collect(Collectors.toList());
    }
}
