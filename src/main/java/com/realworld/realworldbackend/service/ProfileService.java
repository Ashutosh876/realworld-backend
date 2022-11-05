package com.realworld.realworldbackend.service;

import com.realworld.realworldbackend.model.DTO.ProfileDTO;
import com.realworld.realworldbackend.model.entity.FollowEntity;
import com.realworld.realworldbackend.model.entity.UserEntity;
import com.realworld.realworldbackend.repository.FollowsRepository;
import com.realworld.realworldbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private FollowsRepository followsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public ProfileDTO getProfile(String username) {
        ProfileDTO profileDTO = new ProfileDTO();

        UserEntity userEntity = userRepository.findByUsername(username).get();
        profileDTO.setUsername(username);
        profileDTO.setBio(userEntity.getBio());
        profileDTO.setImage(userEntity.getImage());
        profileDTO.setFollowing(is_following(userService.getCurrentUser(), userEntity));

        return profileDTO;
    }

    public Boolean is_following(UserEntity currentUserEntity, UserEntity viewedUserEntity) {
        //return if the current user is following viewedUser
        Optional<FollowEntity> entityOptional = followsRepository.findByFolloweeIdAndFollowerId(viewedUserEntity.getId(), currentUserEntity.getId());

        return entityOptional.isPresent();
    }

    public void followUser(String username) {

        UserEntity currentUserEntity = userService.getCurrentUser();
        UserEntity userEntityToBeFollowed = userRepository.findByUsername(username).get();

        FollowEntity currentUserProfile = new FollowEntity();
        currentUserProfile.setFollowee(userEntityToBeFollowed);
        currentUserProfile.setFollower(currentUserEntity);

        followsRepository.save(currentUserProfile);
    }

    public void unfollowUser(String username) {

        UserEntity currentUserEntity = userService.getCurrentUser();
        UserEntity userEntityToBeUnfollowed = userRepository.findByUsername(username).get();

        Optional<FollowEntity> entryToBeDeleted = followsRepository.findByFolloweeIdAndFollowerId(userEntityToBeUnfollowed.getId(), currentUserEntity.getId());

        if(entryToBeDeleted.isPresent()) followsRepository.delete(entryToBeDeleted.get());
    }

}
