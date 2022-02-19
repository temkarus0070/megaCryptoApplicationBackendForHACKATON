package org.neoflex.megacryptoapplicationbackend.security.Providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.neoflex.megacryptoapplicationbackend.security.JWTAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class JWTAuthProvider implements AuthenticationProvider {
    @Value("${jwt.singing.key}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String jwt = authentication.getCredentials().toString();
        String user = JWT.require(Algorithm.HMAC256(key.getBytes(StandardCharsets.UTF_8)))
                .build()
                .verify(jwt)
                .getSubject();

        JWTAuthentication jwtAuthentication = new JWTAuthentication(jwt, user);
        jwtAuthentication.setAuthenticated(true);
        return jwtAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthentication.class.isAssignableFrom(authentication);
    }
}
