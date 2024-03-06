package com.dsi.project.config;

import com.dsi.project.model.AllUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {


    private AllUser allUser;


    public CustomUserDetails(AllUser user) {
        this.allUser = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        SimpleGrantedAuthority simpleGrantedAuthorityUser = new SimpleGrantedAuthority(allUser.getRole());
        System.out.println("I am here at getting authorities");
        return List.of(simpleGrantedAuthorityUser);
    }

    @Override
    public String getPassword() {
        return allUser.getPassword();
    }

    @Override
    public String getUsername() {
        return allUser.getEmail();
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
