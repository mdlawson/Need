package com.mdlawson.need.article.hn;

import com.mdlawson.need.article.Article;

public class HNArticle extends Article {

    enum Type {ASK, SHOW, JOB, SELF, LINK}

    String id;
    String title;
    String url;
    String points;
    String time;
    String user;
    String comments;
    Type type;

    protected HNArticle id(String id) {
        this.id = id;
        return this;
    }
    protected HNArticle title(String title) {
        this.title = title;
        return this;
    }


    protected HNArticle url(String url) {
        this.url = url;
        return this;
    }

    protected HNArticle points(String points) {
        this.points = points;
        return this;
    }

    protected HNArticle time(String time) {
        this.time = time;
        return this;
    }

    protected HNArticle user(String user) {
        this.user = user;
        return this;
    }
    protected HNArticle type(Type type) {
        this.type = type;
        return this;
    }
    protected HNArticle comments(String comments) {
        this.comments = comments;
        return this;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
