package com.mdlawson.need.article.hn;

import com.mdlawson.need.article.ArticleSource;
import com.mdlawson.need.article.Section;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

public class HNSource extends ArticleSource{

    public static final String url = "https://news.ycombinator.com/";
    public static final String[] sections = {"news","newest","show","shownew","ask","jobs"};

    @Override
    public void loadSections() throws SourceException, NetworkException {
        List<Section> sectionList = new ArrayList<>();
        for (String name : sections) {
            sectionList.add(new HNSection(name, url + name));
        }
        yield(sectionList);
    }

    @Override
    public String getName() {
        return "Hacker News";
    }

    @Override
    public int getColor() {
        return 0xFFFF6600;
    }


}
