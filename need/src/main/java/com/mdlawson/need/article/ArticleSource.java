package com.mdlawson.need.article;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import de.greenrobot.event.EventBus;

import java.util.*;

public abstract class ArticleSource  {

    private List<Section> sections;
    protected EventBus bus;
    protected OkHttpClient client;

    public ArticleSource() {
        sections = new ArrayList<>();
        bus = EventBus.getDefault();
        client = bus.getStickyEvent(OkHttpClient.class);
    }

    public void reset() {
        sections.clear();
        try {
            loadSections();
        } catch (SourceException e) {
            // TODO handle
        } catch (NetworkException e) {
            // TODO handle
        }
    }


    public abstract void loadSections() throws SourceException, NetworkException;

    protected void yield(Section section) {
        sections.add(section);
    }
    protected void yield(List<Section> sections) {
        this.sections.addAll(sections);
    }

    public class NetworkException extends Exception {
    }

    public class SourceException extends Exception {
    }

    public List<Section> getSections() {
        return sections;
    }

    public abstract String getName();
    public abstract int getColor();
}