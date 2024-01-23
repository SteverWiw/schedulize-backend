package com.ajsoftware.backendjwtauth.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user", schema = "ajscore",uniqueConstraints = {@UniqueConstraint(name = "uk_user_name",columnNames = {"username"})})
public class UserEntity implements Serializable, UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",nullable = false,length = 20)
    private String userName;

    @Column(name = "password",nullable = false, length = 200)
    private String password;

    @Column(name = "status",nullable = false,columnDefinition = "CHAR(1)")
    private String status;

    @Column(name = "idrole", columnDefinition = "SERIAL")
    private Long idRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idrole", nullable = false,foreignKey = @ForeignKey(name = "fk_user_role"),updatable = false,insertable = false)
    private RoleEntity role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(role)
                .map(r -> List.of(new SimpleGrantedAuthority(r.getRoleName())))
                .orElse(Collections.emptyList());
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Objects.equals(status, "S");
    }
}
