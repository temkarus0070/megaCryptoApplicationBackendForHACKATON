package org.neoflex.megacryptoapplicationbackend.security.Filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.neoflex.megacryptoapplicationbackend.security.Persistence.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Component
public class LoginFilter extends OncePerRequestFilter {
    private AuthenticationManager authenticationManager;

    @Autowired
    public LoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        BufferedReader stringReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = stringReader.lines().collect(Collectors.joining());
        try {
            User userDetails = new ObjectMapper()
                    .readValue(json, org.neoflex.megacryptoapplicationbackend.security.Persistence.Entity.User.class);
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword()));
            String token = String.format("Bearer %s", String.valueOf(auth.getCredentials()));
            response.getOutputStream()
                    .print(String.format("{\"Authorization\": \"%s\"}", token));

            response.setHeader("username", auth.getPrincipal().toString());
            filterChain.doFilter(request, response);

        } catch (IOException exception) {
            response.setStatus(400);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/register") || request.getHeader("Authorization") != null;
    }
}
