package com.root.Generic.AplicationLayer.JDBC.Dao;

import java.util.List;

import com.root.Generic.AplicationLayer.JDBC.Models.Article;



public interface IArticleDAO {
    List<Article> getAllArticles();
    Article getArticleById(int articleId);
    void addArticle(Article article);
    void updateArticle(Article article);
    void deleteArticle(int articleId);
    boolean articleExists(String title, String category);
}
 