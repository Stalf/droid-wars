package com.droidwars.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Root entity class for registered site users
 */
@ToString(of = {"id", "username", "registerDate", "enabled"})
@Entity
@Table(name="users")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
public class User implements UserDetails{

    /**
     * Identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users")
    @GenericGenerator(name = "users", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
        @Parameter(name="sequence_name", value="users_seq")
    })
    @JsonIgnore
    @Column(updatable = false, nullable = false)
    private long id;
    /**
     * Username used for login purposes
     */
    @JsonIgnore
    @Column(length = 255, unique = true, nullable = false)
    private String username;
    /**
     * Salted user password
     * flagged as not updatable for security reasons
     */
    @JsonIgnore
    @Column(length = 60, nullable = false, updatable = false)
    private String pass;

    /**
     * E-mail
     */
    @JsonIgnore
    @Column(length = 254)
    private String email;
    /**
     * Registration date
     */
    @JsonIgnore
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime registerDate;

    /**
     * Flag to show if the user is active or not
     */
    @JsonIgnore
    @Column(nullable = false)
    private boolean enabled;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //TODO
        return null;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.pass;
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

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPass() {
        return pass;
    }

    @JsonIgnore
    public void setPass(String pass) {
        this.pass = pass;
    }

    @JsonIgnore
    public String getEmail() {
        return email;
    }

    @JsonIgnore
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty
    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    @JsonIgnore
    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    @JsonProperty
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
