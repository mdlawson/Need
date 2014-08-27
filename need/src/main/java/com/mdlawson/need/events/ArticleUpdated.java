package com.mdlawson.need.events;

import com.mdlawson.need.article.Article;

public class ArticleUpdated {
    Article article;

    public ArticleUpdated(Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }
}
