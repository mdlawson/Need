package com.mdlawson.need.article.dummy;

import com.mdlawson.need.article.Article;

public class DummyArticle extends Article{
    String id;
    String title;

    public DummyArticle() {
    }

    protected DummyArticle id(String id) {
        this.id = id;
        return this;
    }
    protected DummyArticle title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public String getTitle() {
        return id;
    }

    @Override
    public String getId() {
        return title;
    }
}
