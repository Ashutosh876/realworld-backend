package com.realworld.realworldbackend.model.DTO;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.realworld.realworldbackend.model.entity.TagEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonTypeName(value = "tags")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT) // this line is the reason for the correct json building with the key as "profile"
public class TagsDTO {

    private List<TagEntity> tagEntityList;
}
