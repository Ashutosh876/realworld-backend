package com.realworld.realworldbackend.repository;

import com.realworld.realworldbackend.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public Optional<UserEntity> findByUsername(@Param("username") String username);

    public Optional<UserEntity> findByEmail(@Param("email") String email);
}
