package com.realworld.realworldbackend.repository;

import com.realworld.realworldbackend.model.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, UUID> {

    Optional<ArticleEntity> findById(UUID id);

    Optional<ArticleEntity> findBySlug(String slug);

}
