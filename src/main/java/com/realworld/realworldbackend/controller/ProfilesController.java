package com.realworld.realworldbackend.controller;

import com.realworld.realworldbackend.model.DTO.ProfileDTO;
import com.realworld.realworldbackend.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController(value = "/api/profiles")
public class ProfilesController {

    @Autowired
    private ProfileService profileService;

    @GetMapping(value = "/test")
    public String test() {
        return "success";
    }

    @GetMapping(value = "/{username}")
    public ProfileDTO getProfile(@PathVariable("username") String username) {

        return profileService.getProfile(username);
    }

    @PostMapping(value = "/{username}/follow")
    public void followUser(@PathVariable("username") String username) {

        profileService.followUser(username);
    }

    @PostMapping(value = "/{username}/unfollow")
    public void unfollowUser(@PathVariable("username") String username) {

        profileService.unfollowUser(username);
    }

}
