package com.realworld.realworldbackend.service;

import com.realworld.realworldbackend.exception.InvalidUserException;
import com.realworld.realworldbackend.model.AuthRequest;
import com.realworld.realworldbackend.model.MyUserDetails;
import com.realworld.realworldbackend.model.entity.UserEntity;
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

    public UserEntity authenticateUser(AuthRequest authRequest) {
        UserEntity userEntity = authRequest.getUser();
        try {
            authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(userEntity.getEmail(), userEntity.getPassword()));
        } catch (BadCredentialsException e) {
            logger.info("Incorrect login credentials entered!! Please Check.");
            throw new BadCredentialsException("Incorrect login credentials entered!! Please Check.");
        }
        return userRepository.findByEmail(userEntity.getEmail()).get();
    }

    public UserEntity allotJWT(UserEntity userEntity) {
        final MyUserDetails userDetails = (MyUserDetails) myUserDetailsService.loadUserByUsername(userEntity.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        userEntity.setToken(jwt);
        return userEntity;
    }

    public UserEntity registerUser(AuthRequest authRequest) throws InvalidUserException {
        //you can check if the username already exists, can check if the password is not strong, can check if the email id is not valid
        //ignoring that as of now
        UserEntity userEntity = authRequest.getUser();
        if(validateUser(userEntity)) {
            userRepository.save(userEntity);
            return userEntity;
        } else {
            throw new InvalidUserException("User is invalid!! Please check.");
        }
    }

    private boolean validateUser(UserEntity userEntity) {
        //TODO
        //validation
        return true;
    }

    public UserEntity getCurrentUser() {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserEntity> user = userRepository.findByEmail(myUserDetails.getEmail());
        return user.get();
    }

    public UserEntity updateUser(AuthRequest authRequest) {
        UserEntity userEntity = getCurrentUser();
        if(null != authRequest.getUser().getPassword()) userEntity.setPassword(authRequest.getUser().getPassword());
        if(null != authRequest.getUser().getEmail()) userEntity.setEmail(authRequest.getUser().getEmail());
        if(null != authRequest.getUser().getUsername()) userEntity.setUsername(authRequest.getUser().getUsername());
        if(null != authRequest.getUser().getBio()) userEntity.setBio(authRequest.getUser().getBio());
        if(null != authRequest.getUser().getImage()) userEntity.setImage(authRequest.getUser().getImage());

        userRepository.save(userEntity);
        return userEntity;
    }
}
