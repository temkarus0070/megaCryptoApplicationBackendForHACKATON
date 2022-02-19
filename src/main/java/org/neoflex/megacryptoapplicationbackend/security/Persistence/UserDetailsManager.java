package org.neoflex.megacryptoapplicationbackend.security.Persistence;

import org.neoflex.megacryptoapplicationbackend.security.Persistence.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsManager implements org.springframework.security.provisioning.UserDetailsManager {
    private UserService userService;

    @Autowired
    public UserDetailsManager(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void createUser(UserDetails user) {
        userService.save(user);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return userService.find(username).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetails> userOptional = userService.find(username);
        if (userOptional.isEmpty())
            throw new UsernameNotFoundException(String.format("user with name = %s not found", username));
        return userOptional.get();
    }
}
