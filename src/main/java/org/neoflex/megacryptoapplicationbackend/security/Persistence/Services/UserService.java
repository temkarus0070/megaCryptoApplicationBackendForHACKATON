package org.neoflex.megacryptoapplicationbackend.security.Persistence.Services;

import org.neoflex.megacryptoapplicationbackend.security.Persistence.Entity.User;
import org.neoflex.megacryptoapplicationbackend.security.Persistence.Repositories.UserRepository;
import org.neoflex.megacryptoapplicationbackend.security.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<org.springframework.security.core.userdetails.UserDetails> find(String username) {
        Optional<User> userOptional = userRepository.findById(username);
        return userOptional.map(UserDetails::new);
    }

    public void save(org.springframework.security.core.userdetails.UserDetails userDetails) {
        userRepository.save(new User(userDetails.getUsername(), passwordEncoder.encode(userDetails.getPassword())));
    }
}
