package com.root.Generic.AplicationLayer.JDBC.Services;

import java.util.List;

import com.root.Generic.AplicationLayer.JDBC.Models.Article;



public interface IArticleService {
     List<Article> getAllArticles();
     Article getArticleById(int articleId);
     boolean addArticle(Article article);
     void updateArticle(Article article);
     void deleteArticle(int articleId);
}
