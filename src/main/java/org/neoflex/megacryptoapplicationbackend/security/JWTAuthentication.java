package org.neoflex.megacryptoapplicationbackend.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JWTAuthentication implements Authentication {
    private String jwt;
    private String username;
    private boolean isAuthenticated;

    private List<GrantedAuthority> authorities = new ArrayList<>();

    public JWTAuthentication(String jwt) {
        this.jwt = jwt;
    }

    public JWTAuthentication(String jwt, String username) {
        this.jwt = jwt;
        this.username = username;
        this.authorities.add(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return jwt;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return username;
    }
}
