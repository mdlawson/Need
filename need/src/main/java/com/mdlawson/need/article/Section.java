package com.mdlawson.need.article;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.BaseAdapter;
import com.mdlawson.need.events.SectionUpdated;
import com.squareup.okhttp.OkHttpClient;
import de.greenrobot.event.EventBus;

import java.util.*;


public abstract class Section {

    private List<Article> articles;
    private boolean hasMore;
    protected EventBus bus;
    protected OkHttpClient client;

    private int page = 0;

    public Section() {
        bus = EventBus.getDefault();
        articles = new ArrayList<>();
        client = bus.getStickyEvent(OkHttpClient.class);
    }

    public void reload() {
        hasMore = true;
        page = 0;
        articles.clear();
        loadMore();
    }

    public void loadMore() {
        if (hasMore) {
            page++;
            get(page);
        }
    }

    public abstract void get(int page);

    protected void yield(Article article) {
        articles.add(article);
        bus.post(new SectionUpdated(this));
    }
    protected void yield(List<Article> articles) {
        this.articles.addAll(articles);
        bus.post(new SectionUpdated(this));
    }
    protected void error(Throwable error) {
        // TODO handle
    }
    protected void end() {
        hasMore = false;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public boolean hasMore() {
        return hasMore;
    }

    public int getPage() {
        return page;
    }

    public abstract String getName();
}
