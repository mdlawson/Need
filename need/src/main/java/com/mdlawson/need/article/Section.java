package com.mdlawson.need.article;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.*;


public abstract class Section {
    public enum Status {END, CONTINUE}

    private List<Article> articles;
    private Status status;

    private int page = 0;

    public Section() {
        articles = new ArrayList<>();
    }

    public void reload() {
        page = 0;
        articles.clear();
        loadMore();
    }

    public void loadMore() {
        page++;
        try {
            status = get(page);
        } catch (ArticleSource.SourceException e) {
            // TODO handle
        } catch (ArticleSource.NetworkException e) {
            // TODO handle
        }
    }

    public abstract Status get(int page) throws ArticleSource.SourceException, ArticleSource.NetworkException;

    protected void yield(Article article) {
        articles.add(article);
    }
    protected void yield(List<Article> articles) {
        this.articles.addAll(articles);
    }


    public List<Article> getArticles() {
        return articles;
    }

    public Status getStatus() {
        return status;
    }

    public int getPage() {
        return page;
    }

    public abstract String getName();
}
