package com.realworld.realworldbackend.repository;

import com.realworld.realworldbackend.model.entity.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FollowsRepository extends JpaRepository<FollowEntity, UUID> {

    Optional<FollowEntity> findByFolloweeIdAndFollowerId(UUID  followeeId, UUID followerId);

}
