package org.neoflex.megacryptoapplicationbackend.security;

import org.neoflex.megacryptoapplicationbackend.security.Filters.JWTAuthFilter;
import org.neoflex.megacryptoapplicationbackend.security.Filters.LoginFilter;
import org.neoflex.megacryptoapplicationbackend.security.Providers.JWTAuthProvider;
import org.neoflex.megacryptoapplicationbackend.security.Providers.LoginAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private JWTAuthProvider jwtAuthProvider;

    @Autowired
    private LoginAuthProvider loginAuthProvider;

    private JWTAuthFilter jwtAuthFilter;

    private LoginFilter loginFilter;


    public SecurityConfig(@Lazy JWTAuthFilter jwtAuthFilter, @Lazy LoginFilter loginFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.loginFilter = loginFilter;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(loginAuthProvider)
                .authenticationProvider(jwtAuthProvider);


    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.addFilterAt(loginFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(jwtAuthFilter, BasicAuthenticationFilter.class);

        http.authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}
