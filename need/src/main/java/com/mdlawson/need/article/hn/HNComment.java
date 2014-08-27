package com.mdlawson.need.article.hn;

import com.mdlawson.need.article.Comment;

public class HNComment extends Comment {

    String id;
    String author;
    String text;
    String date;
    String points;

    @Override
    public String getText() {
        return text;
    }
}
