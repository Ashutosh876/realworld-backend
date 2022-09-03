package com.realworld.realworldbackend.repository;

import com.realworld.realworldbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(@Param("username") String username);

    public Optional<User> findByEmail(@Param("email") String email);
}
