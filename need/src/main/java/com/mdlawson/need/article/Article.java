package com.mdlawson.need.article;

import com.mdlawson.need.events.ArticleUpdated;
import com.squareup.okhttp.OkHttpClient;
import de.greenrobot.event.EventBus;

import java.util.List;


public abstract class Article {

    protected EventBus bus;
    protected OkHttpClient client;

    protected Article() {
        bus = EventBus.getDefault();
        client = bus.getStickyEvent(OkHttpClient.class);
    }

    public abstract String getId();

    public abstract String getTitle();

    public abstract void getDetails();

    public abstract List<Comment> getComments();

    protected void notifyUpdated() {
        bus.post(new ArticleUpdated(this));
    }
}
