package com.droidwars.core.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

/**
 * Security roles
 */
@Entity
@Table(name = "roles")
@Immutable
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "code")
public class Role {

    final static public Role ADMIN = new Role(UserRole.ROLE_ADMIN, "Administrator");
    final static public Role USER = new Role(UserRole.ROLE_USER, "Player");

    /**
     * Role code (ROLE_ADMIN/ROLE_USER
     */
    @Id
    @Column(name = "code")
    @Enumerated(EnumType.STRING)
    private UserRole code;

    /**
     * Human-readable role name
     */
    @Column(name="name", nullable = false)
    private String name;

}
