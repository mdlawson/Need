package com.mdlawson.need.article.dummy;

import com.mdlawson.need.article.Article;
import com.mdlawson.need.article.ArticleSource;
import com.mdlawson.need.article.Section;

import java.util.ArrayList;
import java.util.List;

public class DummySection extends Section {
    static final int PER_PAGE = 20;
    private int num;

    public DummySection(int num) {
        this.num = num;
    }

    @Override
    public Status get(int page) throws ArticleSource.SourceException, ArticleSource.NetworkException {
        List<Article> articles = new ArrayList<>();
        for (int count = (page - 1) * PER_PAGE; count <= page * PER_PAGE; count++) {
            articles.add(new Article().id(String.valueOf(count)).title("Dummy Article " + count));
        }
        yield(articles);
        return Status.CONTINUE;
    }

    @Override
    public String getName() {
        return "Section " + num;
    }
}
