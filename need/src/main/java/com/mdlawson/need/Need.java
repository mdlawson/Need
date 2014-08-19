package com.mdlawson.need;

import android.app.Application;
import com.mdlawson.need.article.ArticleSource;
import com.mdlawson.need.article.dummy.DummySource;

import java.util.ArrayList;
import java.util.List;

public class Need extends Application {

    List<ArticleSource> sources;

    @Override
    public void onCreate() {
        super.onCreate();

        sources = new ArrayList<>();
        sources.add(new DummySource());

        for (ArticleSource source : sources) {
            try {
                source.loadSections();
            } catch (Exception e) {
                // bum TODO
            }
        }
    }

    public List<ArticleSource> getSources() {
        return sources;
    }
}
