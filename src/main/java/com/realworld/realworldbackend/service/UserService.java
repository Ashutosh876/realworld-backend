package com.realworld.realworldbackend.service;

import com.realworld.realworldbackend.exception.InvalidUserException;
import com.realworld.realworldbackend.model.AuthRequest;
import com.realworld.realworldbackend.model.MyUserDetails;
import com.realworld.realworldbackend.model.User;
import com.realworld.realworldbackend.repository.UserRepository;
import com.realworld.realworldbackend.security.MyUserDetailsService;
import com.realworld.realworldbackend.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    final Logger logger = LoggerFactory.getLogger(UserService.class);

    public User authenticateUser(AuthRequest authRequest) {
        User user = authRequest.getUser();
        try {
            authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        } catch (BadCredentialsException e) {
            logger.info("Incorrect login credentials entered!! Please Check.");
            throw new BadCredentialsException("Incorrect login credentials entered!! Please Check.");
        }
        return userRepository.findByEmail(user.getEmail()).get();
    }

    public User allotJWT(User user) {
        final MyUserDetails userDetails = (MyUserDetails) myUserDetailsService.loadUserByUsername(user.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        user.setToken(jwt);
        return user;
    }

    public User registerUser(AuthRequest authRequest) throws InvalidUserException {
        //you can check if the username already exists, can check if the password is not strong, can check if the email id is not valid
        //ignoring that as of now
        User user = authRequest.getUser();
        if(validateUser(user)) {
            userRepository.save(user);
            return user;
        } else {
            throw new InvalidUserException("User is invalid!! Please check.");
        }
    }

    private boolean validateUser(User user) {
        //TODO
        //validation
        return true;
    }

    public User getCurrentUser() {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByEmail(myUserDetails.getEmail());
        return user.get();
    }

    public User updateUser(AuthRequest authRequest) {
        User user = getCurrentUser();
        if(null != authRequest.getUser().getPassword()) user.setPassword(authRequest.getUser().getPassword());
        if(null != authRequest.getUser().getEmail()) user.setEmail(authRequest.getUser().getEmail());
        if(null != authRequest.getUser().getUsername()) user.setUsername(authRequest.getUser().getUsername());
        if(null != authRequest.getUser().getBio()) user.setBio(authRequest.getUser().getBio());
        if(null != authRequest.getUser().getImage()) user.setImage(authRequest.getUser().getImage());

        userRepository.save(user);
        return user;
    }
}
