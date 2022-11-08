package com.realworld.realworldbackend.controller;

import com.realworld.realworldbackend.model.DTO.ArticleDTO;
import com.realworld.realworldbackend.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/articles")
public class ArticleController {

    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/{slug}")
    public ArticleDTO getArticle(@PathVariable("slug") String slug) {
        ArticleDTO articleDTO = null;
        try {
            articleDTO = articleService.getArticle(slug);
        } catch (Exception e) {
            logger.info("Article not found!!");
        }
        return articleDTO;
    }

    @PostMapping(value = "/")
    public ArticleDTO createArticle(@RequestBody ArticleDTO articleDTO) {

        ArticleDTO result = null;
        try {
            result = articleService.createArticle(articleDTO);
        } catch (Exception e) {
            logger.info("Could not create article : ", e);
        }
        return result;
    }

//    @PutMapping(value = "/")
//    public ArticleDTO updateArticle(@RequestBody ArticleDTO articleDTO) {
//
//    }
//
//    @DeleteMapping("/{slug}")
//    public ResponseBody deleteArticle(@PathVariable("slug") String slug) {
//
//    }

}
