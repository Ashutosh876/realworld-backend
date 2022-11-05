package com.realworld.realworldbackend.security;

import com.realworld.realworldbackend.model.MyUserDetails;
import com.realworld.realworldbackend.model.entity.UserEntity;
import com.realworld.realworldbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UserEntity> user = userRepository.findByEmail(email);

        user.orElseThrow(() -> new UsernameNotFoundException("User not found!! " + email));

        return user.map(MyUserDetails::new).get();
    }
}
