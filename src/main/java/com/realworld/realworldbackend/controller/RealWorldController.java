package com.realworld.realworldbackend.controller;

import com.realworld.realworldbackend.exception.InvalidUserException;
import com.realworld.realworldbackend.model.AuthRequest;
import com.realworld.realworldbackend.model.MyUserDetails;
import com.realworld.realworldbackend.model.User;
import com.realworld.realworldbackend.repository.UserRepository;
import com.realworld.realworldbackend.security.MyUserDetailsService;
import com.realworld.realworldbackend.service.UserService;
import com.realworld.realworldbackend.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController("/")
public class RealWorldController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    final Logger logger = LoggerFactory.getLogger(RealWorldController.class);

    //Login API
    @PostMapping(value = "api/users/login", headers="Accept=application/json")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest authRequest) throws Exception {
        logger.info("START : Logging in user");
        User user;
        try {
            user = userService.authenticateUser(authRequest);
            logger.info("User : {} Authenticated!!", user.getUsername());
            user = userService.allotJWT(user);
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        } catch (BadCredentialsException e) {
            return ResponseEntity.ok(e);
        }
    }

    //Registration API
    @PostMapping(value = "api/users/register", headers="Accept=application/json")
    public ResponseEntity<?> registerUser(@RequestBody AuthRequest authRequest) {
        try {
            User user = userService.registerUser(authRequest);
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        } catch (InvalidUserException e) {
            return ResponseEntity.ok(e);
        }
    }

    @GetMapping("/user")
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody AuthRequest authRequest) {
        User user = userService.updateUser(authRequest);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping("register")
    public String test() {
        return "register";
    }

}
