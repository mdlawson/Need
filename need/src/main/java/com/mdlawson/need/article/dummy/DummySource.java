package com.mdlawson.need.article.dummy;

import android.graphics.Color;
import com.mdlawson.need.article.Article;
import com.mdlawson.need.article.ArticleSource;

import java.util.ArrayList;
import java.util.List;

public class DummySource extends ArticleSource {

    @Override
    public void loadSections() throws SourceException, NetworkException {
        yield(new DummySection(1));
        yield(new DummySection(2));
        yield(new DummySection(3));
    }

    @Override
    public String getName() {
        return "Dummy";
    }

    @Override
    public int getColor() {
        return 0xFFFF6600;
    }
}
