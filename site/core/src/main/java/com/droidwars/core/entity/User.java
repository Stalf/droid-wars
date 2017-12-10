package com.droidwars.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Sets;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Root entity class for registered site users
 */
@ToString(of = {"id", "username", "email", "registerDate", "enabled"})
@EqualsAndHashCode(of = {"username"})
@Entity
@Table(name="users")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
public class User{

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
     * E-mail, optional
     */
    @JsonIgnore
    @Column(length = 254)
    private String email;

    /**
     * Registration date
     */
    @JsonIgnore
    @Column(nullable = false, updatable = false)
    private LocalDateTime registerDate;

    /**
     * Flag to show if the user is active or not
     */
    @JsonIgnore
    @Column(nullable = false)
    private boolean enabled;

    /**
     * User roles
     */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "role_code", referencedColumnName = "code")})
    private Set<Role> roles = Sets.newHashSet();

    /**
     * Password reset date
     */
    @JsonIgnore
    @Column(nullable = false)
    private LocalDateTime passwordResetDate;

    /**
     * Creates a new User instance with ROLE_USER authority
     * @param username unique username
     * @param password hashed and salted password
     * @param email user email, optional
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.pass = password;
        this.email = email;
        this.enabled = true;
        this.registerDate = this.passwordResetDate = LocalDateTime.now();
        this.roles.add(Role.USER);
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
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @JsonIgnore
    public Set<Role> getRoles() {
        return roles;
    }

    @JsonIgnore
    public LocalDateTime getPasswordResetDate() {
        return passwordResetDate;
    }

    @JsonIgnore
    public void setPasswordResetDate(LocalDateTime passwordResetDate) {
        this.passwordResetDate = passwordResetDate;
    }


}
