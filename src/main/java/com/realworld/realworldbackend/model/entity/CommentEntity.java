package com.realworld.realworldbackend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {

    private String commentBody;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private ArticleEntity articleEntity;

}
