package com.mdlawson.need.events;

import com.mdlawson.need.article.Article;

public class ShowArticle {
    Article article;

    public ShowArticle(Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }
}
