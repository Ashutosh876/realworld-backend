package com.realworld.realworldbackend.model.DTO;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonTypeName(value = "profile")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT) // this line is the reason for the correct json building with the key as "profile"
public class ProfileDTO {

        private String username;
        private String bio;
        private String image;
        private Boolean following; //whether current user is following the user queried for.
}
