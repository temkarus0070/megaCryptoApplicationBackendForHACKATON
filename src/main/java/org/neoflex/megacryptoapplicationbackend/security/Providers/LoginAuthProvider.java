package org.neoflex.megacryptoapplicationbackend.security.Providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.neoflex.megacryptoapplicationbackend.security.JWTAuthentication;
import org.neoflex.megacryptoapplicationbackend.security.Persistence.UserDetailsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class LoginAuthProvider implements AuthenticationProvider {
    @Value("${jwt.singing.key}")
    private String key;


    private UserDetailsManager userDetailsManager;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public LoginAuthProvider(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails user = userDetailsManager.loadUserByUsername(authentication.getName());
        if (passwordEncoder.matches(String.valueOf(authentication.getCredentials()), user.getPassword())) {
            String jwtToken = JWT.create()
                    .withSubject(String.valueOf(authentication.getPrincipal()))
                    .sign(Algorithm.HMAC256(key.getBytes(StandardCharsets.UTF_8)));
            return new JWTAuthentication(jwtToken);
        } else
            throw new BadCredentialsException("username or password was incorrect");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
