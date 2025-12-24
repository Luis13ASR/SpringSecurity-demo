package com.example.demo.Entities;

import java.util.Collection;
import java.util.Collections;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ReadOnlyProperty
    private Long id;

    @Email
    @NotBlank
    @Size(max = 80)
    @Column(unique = true, nullable = false, name = "email")
    private String email;

    @NotBlank
    @Size(max = 30)
    @Column(unique = true, nullable = false, name = "username")
    private String username;

    @NotBlank
    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "id_rol",
        referencedColumnName = "id",
        nullable = false
    )
    private RolesEntity idRol;

    @Override
    public String getUsername(){
        return this.username;
    }

    @Override
    public String getPassword(){
        return this.password;
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
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        String roleName = idRol.getName();
        return Collections.singleton(new SimpleGrantedAuthority(roleName));
    }
}
