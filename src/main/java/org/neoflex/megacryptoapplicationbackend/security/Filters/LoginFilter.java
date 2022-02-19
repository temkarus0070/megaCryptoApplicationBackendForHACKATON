package org.neoflex.megacryptoapplicationbackend.security.Filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFilter extends OncePerRequestFilter {
    private AuthenticationManager authenticationManager;

    @Autowired
    public LoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            User userDetails = new ObjectMapper()
                    .readValue(request.getInputStream(), User.class);
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities()));
            response.setHeader("Authentication", String.format("Bearer %s", String.valueOf(auth.getCredentials())));
            response.setHeader("username", auth.getPrincipal().toString());
            filterChain.doFilter(request, response);

        } catch (IOException exception) {
            response.setStatus(400);
        }
    }
}
