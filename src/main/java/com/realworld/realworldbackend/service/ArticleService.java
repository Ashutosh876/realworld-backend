package com.realworld.realworldbackend.service;

import com.realworld.realworldbackend.exception.ArticleNotFoundException;
import com.realworld.realworldbackend.model.DTO.ArticleDTO;
import com.realworld.realworldbackend.model.entity.ArticleEntity;
import com.realworld.realworldbackend.model.entity.TagEntity;
import com.realworld.realworldbackend.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public ArticleDTO getArticle(String slug) throws ArticleNotFoundException {
        Optional<ArticleEntity> articleEntityOptional = articleRepository.findBySlug(slug);

        if(articleEntityOptional.isPresent()) {
            ArticleDTO articleDTO = new ArticleDTO();
            ArticleEntity articleEntity = articleEntityOptional.get();
            articleDTO.setBody(articleEntity.getBody());
            articleDTO.setDescription(articleEntity.getDescription());
            articleDTO.setSlug(articleEntity.getSlug());
            articleDTO.setTitle(articleEntity.getTitle());
            articleDTO.setTagList(articleEntity.getTagList().stream().map(TagEntity::getTagName).collect(Collectors.toList()));
            return articleDTO;
        } else {
            throw new ArticleNotFoundException("Requested article does not exists!!");
        }
    }

    public ArticleDTO createArticle(ArticleDTO articleDTO) {

        articleDTO.setSlug(String.join("-", articleDTO.getTitle().split(" ")));

        ArticleEntity articleEntity = ArticleEntity.builder()
                .title(articleDTO.getTitle())
                .description(articleDTO.getDescription())
                .body(articleDTO.getBody())
                .slug(articleDTO.getSlug())
                .build();

        List<TagEntity> tagEntityList = new ArrayList<>();
        for(String tag : articleDTO.getTagList()) {
            tagEntityList.add(TagEntity.builder().tagName(tag).articleEntity(articleEntity).build());
        }
        articleEntity.setTagList(tagEntityList);

        articleRepository.save(articleEntity);
        return articleDTO;
    }

//    public ArticleDTO convertArticleEntityToDTO(ArticleEntity articleEntity) {
//
//        ArticleDTO articleDTO = ArticleDTO.builder()
//                .slug(articleEntity.getSlug())
//                .title(articleEntity.getTitle())
//                .description(articleEntity.getDescription())
//                .body(articleEntity.getBody())
//                .tagList()
//                .build();
//
//        return articleDTO;
//
//    }
}
