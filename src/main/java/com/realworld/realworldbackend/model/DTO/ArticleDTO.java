package com.realworld.realworldbackend.model.DTO;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.realworld.realworldbackend.model.entity.TagEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonTypeName(value = "article")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT) // this line is the reason for the correct json building with the key as "profile"
public class ArticleDTO {

    private String slug;
    private String title;
    private String description;
    private String body;
    private List<String> tagList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean favorited;
    private Integer favoritesCount;
    private ProfileDTO profileDTO;
}
