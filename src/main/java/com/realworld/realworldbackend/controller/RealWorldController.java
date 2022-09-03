package com.realworld.realworldbackend.controller;

import com.realworld.realworldbackend.model.AuthRequest;
import com.realworld.realworldbackend.model.MyUserDetails;
import com.realworld.realworldbackend.model.User;
import com.realworld.realworldbackend.repository.UserRepository;
import com.realworld.realworldbackend.security.MyUserDetailsService;
import com.realworld.realworldbackend.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    final Logger logger = LoggerFactory.getLogger(RealWorldController.class);

    //Login API
    @PostMapping(value = "api/users/login", headers="Accept=application/json")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest authRequest) throws Exception {
        User user = authRequest.getUser();
        //user authentication manager to authenticate against the password
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getUser().getEmail(), authRequest.getUser().getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //once authenticated, return the user with the jwt. so first generate jwt

        final MyUserDetails userDetails = (MyUserDetails) myUserDetailsService.loadUserByUsername(authRequest.getUser().getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        user.setToken(jwt);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    //Registration API
    @PostMapping(value = "api/users/register", headers="Accept=application/json")
    public ResponseEntity<?> registerUser(@RequestBody AuthRequest authRequest) {
        //you can check if the username already exists, can check if the password is not strong, can check if the email id is not valid
        //ignoring that as of now

        User user = authRequest.getUser();
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping("register")
    public String test() {
        return "register";
    }

}
