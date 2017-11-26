package com.droidwars.core.entity;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Root entity class for registered site users
 */
@Getter
@Entity
@Table(name="users")
public class User implements UserDetails{

    /**
     * Identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users")
    @GenericGenerator(name = "users", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
        @Parameter(name="sequence_name", value="users_seq")
    })
    @Column(updatable = false, nullable = false)
    private long id;
    /**
     * Username used for login purposes
     */
    @Column(length = 255, unique = true, nullable = false)
    private String username;
    /**
     * Salted user password
     * flagged as not updateable for security reasons
     */
    @Column(length = 60, nullable = false, updatable = false)
    private String pass;

    public String getPass() {
        return null;
    }

    /**
     * E-mail
     */
    @Column(length = 254)
    private String email;
    /**
     * Registration date
     */
    @Column(nullable = false)
    private LocalDateTime registerDate;

    /**
     * Flag to show if the user is active or not
     */
    @Column(nullable = false)
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //TODO
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
