package com.mdlawson.need.events;

import com.mdlawson.need.article.Article;
import com.mdlawson.need.article.Section;

import java.util.List;

public class SectionUpdated {
    Section section;

    public SectionUpdated(Section section) {
        this.section = section;
    }

    public Section getSection() {
        return section;
    }
}
