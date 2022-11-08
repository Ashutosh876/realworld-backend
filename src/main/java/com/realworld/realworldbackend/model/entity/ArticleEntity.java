package com.realworld.realworldbackend.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "articles")
public class ArticleEntity extends BaseEntity {

    private String slug;
    private String title;
    private String description;
    private String body;
    @OneToMany(cascade = CascadeType.ALL)
    private List<TagEntity> tagList;
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<CommentEntity> commentList;

}
