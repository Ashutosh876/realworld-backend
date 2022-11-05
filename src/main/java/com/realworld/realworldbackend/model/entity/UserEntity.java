package com.realworld.realworldbackend.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_table")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "uuid")
    @Type(type = "pg-uuid")
    private UUID id;
    private String username;
    private String email;
    private String token;
    private String bio;
    private String password;
    private String image;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
