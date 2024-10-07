package com.industrieshf.springboot.authmicroservice.springboot_authmicroservice.models;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import jakarta.persistence.*;

@Entity
@Table(name = "users") // Nombre de la tabla en la base de datos
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String firstName;
    private String lastName;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Si tienes roles, aquí los retornas. Por simplicidad, puedes retornar una
        // lista vacía si no manejas roles.
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public void setUserName(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
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

}
