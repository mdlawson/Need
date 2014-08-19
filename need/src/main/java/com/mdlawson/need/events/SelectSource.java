package com.mdlawson.need.events;

import com.mdlawson.need.article.ArticleSource;

public class SelectSource {
    private ArticleSource source;

    public SelectSource(ArticleSource source) {
        this.source = source;
    }

    public ArticleSource getSource() {
        return source;
    }
}
