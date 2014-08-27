package com.mdlawson.need.article.hn;

import com.mdlawson.need.article.Comment;

import java.util.ArrayList;
import java.util.List;

public class HNCommentsPage {
    String html;
    List<Comment> comments;

    public HNCommentsPage(String html) {
        this.html = html;
        comments = new ArrayList<>();

        parsePage(html);
    }

    static final String delim = "<td><img src=\"s.gif\" height=\"1\" width=\"";

    private void parsePage(String html) {
        int start = html.indexOf(delim) + delim.length();
        int end;
        while ((end = html.indexOf(delim, start)) != -1) {
            parseComment(html.substring(start, end));
            start = end + delim.length();
        }
    }

    static final char quote = '\"';
    static final String userStart = "<a href=\"user?id=";
    static final String linkEnd = "</a>";
    static final char timeEnd = '|';
    static final String commentStart = "<span class=\"comment\">";
    static final String commentEnd = "</span>";
    static final String deleted = "<span class=\"comment\">[deleted]</span>";

    private void parseComment(String html){
        if (html.contains(deleted)) return;

        HNComment comment = new HNComment();

        int start, end;
        // depth
        start = 0;
        end = html.indexOf(quote, start);
        String width = html.substring(start, end);
        comment.setDepth(Integer.valueOf(width) / 40);

        // author
        start = html.indexOf(userStart, end) + userStart.length();
        end = html.indexOf(quote, start);
        comment.author = html.substring(start, end);

        // time
        start = html.indexOf(linkEnd, end) + linkEnd.length();
        end = html.indexOf(timeEnd, start);
        comment.date = html.substring(start, end);

        // text
        start = html.indexOf(commentStart, end) + commentStart.length();
        end = html.indexOf(commentEnd, start);
        comment.text = html.substring(start, end);

        comments.add(comment);
    }

    public List<Comment> getComments() {
        return comments;
    }
}
