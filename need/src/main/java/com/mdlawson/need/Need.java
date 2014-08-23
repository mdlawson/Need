package com.mdlawson.need;

import android.app.Application;
import com.mdlawson.need.article.ArticleSource;
import com.mdlawson.need.article.dummy.DummySource;
import com.mdlawson.need.article.hn.HNSource;
import com.squareup.okhttp.OkHttpClient;
import de.greenrobot.event.EventBus;

import java.util.ArrayList;
import java.util.List;

public class Need extends Application {

    List<ArticleSource> sources;

    @Override
    public void onCreate() {
        super.onCreate();

        EventBus.getDefault().postSticky(new OkHttpClient());

        sources = new ArrayList<>();
        sources.add(new DummySource());
        sources.add(new HNSource());

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
