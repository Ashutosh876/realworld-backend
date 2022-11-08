package com.realworld.realworldbackend.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tags")
public class TagEntity extends BaseEntity {

    private String tagName; //many to many relation with articles

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private ArticleEntity articleEntity;

}
