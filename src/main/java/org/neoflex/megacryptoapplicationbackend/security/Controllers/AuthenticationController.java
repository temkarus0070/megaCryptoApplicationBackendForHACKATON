package org.neoflex.megacryptoapplicationbackend.security.Controllers;

import org.neoflex.megacryptoapplicationbackend.security.Persistence.Entity.User;
import org.neoflex.megacryptoapplicationbackend.security.Persistence.Services.UserService;
import org.neoflex.megacryptoapplicationbackend.security.UserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(User user) {
        if (userService.find(user.getUsername()).isPresent()) {
            return new ResponseEntity("{error:'this username is already taken'}", HttpStatus.BAD_REQUEST);
        } else
            userService.save(new UserDetails(user));
        return new ResponseEntity(HttpStatus.OK);
    }
}
