package com.ecommerceApp.ecommerceApp.security;

import com.ecommerceApp.ecommerceApp.entities.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class AppUser implements UserDetails {
    private Long id;

    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String password;

    private boolean isDeleted;
    private boolean isActive;
    private boolean isExpired;
    private boolean isLocked;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    Set<Role> roles;
    public AppUser() {
    }

//    public AppUser(Users user){
//        this.id = user.getId();
//        this.username = user.getEmail();
//        this.firstName = user.getFirstName();
//        this.middleName = user.getMiddleName();
//        this.lastName = user.getLastName();
//        this.password = user.getPassword();
//        this.isActive = user.isActive();
//        this.isDeleted = user.isDeleted();
//        this.isExpired = user.isExpired();
//        this.isLocked = user.isLocked();
//        this.isAccountNonExpired = user.isAccountNonExpired();
//        this.isAccountNonLocked = user.isAccountNonLocked();
//        this.isCredentialsNonExpired = user.isCredentialsNonExpired();
//        this.isEnabled = user.isEnabled();
//    }
    public AppUser(String username, String password, Set<Role> grantAuthorities,boolean isActive) {
        this.username = username;
        this.password = password;
        this.roles = grantAuthorities;
        this.isActive=isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return isActive;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", isDeleted=" + isDeleted +
                ", isActive=" + isActive +
                ", isExpired=" + isExpired +
                ", isLocked=" + isLocked +
                ", isAccountNonExpired=" + isAccountNonExpired +
                ", isAccountNonLocked=" + isAccountNonLocked +
                ", isCredentialsNonExpired=" + isCredentialsNonExpired +
                ", isEnabled=" + isEnabled +
                ", roles=" + roles +
                '}';
    }
}