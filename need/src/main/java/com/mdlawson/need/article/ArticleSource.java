package com.mdlawson.need.article;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.*;

public abstract class ArticleSource  {

    private List<Section> sections;

    public ArticleSource() {
        sections = new ArrayList<>();
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
    public abstract Color getColor();
}