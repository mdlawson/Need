package com.mdlawson.need.article.hn;

import com.mdlawson.need.article.Article;

import java.util.ArrayList;
import java.util.List;

public class HNPage {
    String html;
    boolean hasMore;

    List<Article> articles;

    public HNPage(String html) {
        this.html = html;
        articles = new ArrayList<>();
        parsePage(html);
    }

    static final String delim = "<tr><td align=\"right\" valign=\"top\" class=\"title\">";

    private void parsePage(String html) {
        int start = html.indexOf(delim) + delim.length();
        int end;
        while ((end = html.indexOf(delim, start)) != -1) {
            parsePost(html.substring(start, end));
            start = end + delim.length();
        }
    }


    static final char quote = '\"';
    static final String idStart = "<a id=\"up_";
    static final String urlStart = "<a href=\"";
    static final char endTag = '>';
    static final String linkEnd = "</a>";
    static final String domainStart = " (";
    static final String domainEnd = ") ";
    static final String pointsEnd = " point";
    static final String userStart = "<a href=\"user?id=";
    static final char timeEnd = '|';
    static final String commentsEnd = " comment";

    // holy shit this parser is fragile. Help.
    private void parsePost(String html) {
        if (html.contains("rel=\"nofollow\">More</a>")) {
            hasMore = true; // TODO follow page links
            return;
        }

        HNArticle article = new HNArticle();


        int start, end;
        boolean job = false;
        // id
        start = html.indexOf(idStart) + idStart.length();
        end = html.indexOf(quote, start);
        article.id = html.substring(start, end);
        // url
        start = html.indexOf(urlStart, end + 1) + urlStart.length(); // 1 = quote length
        end = html.indexOf(quote, start);
        article.url = html.substring(start, end);
        // title
        start = html.indexOf(endTag, end + 1) + 1;
        end = html.indexOf(linkEnd, start);
        article.title = html.substring(start, end);

        // domain
        start = html.indexOf(domainStart, end + linkEnd.length());
        if (start == -1) {
            article.type = HNArticle.Type.SELF;
        } else {
            end = html.indexOf(domainEnd, start + domainStart.length());
            article.type = HNArticle.Type.LINK;
        }
        if (article.title.startsWith("Show HN:")) {
            article.type = HNArticle.Type.SHOW;
        }
        if (article.title.startsWith("Ask HN:")) {
            article.type = HNArticle.Type.ASK;
        }
        // points
        end = html.indexOf(pointsEnd, end + linkEnd.length());
        // if points are disabled, its a job post and we don't have an author, or an id;
        if (end == -1) {
            job = true;
            article.type = HNArticle.Type.JOB;
            article.id = null;
        } else {
            start = html.lastIndexOf(endTag, end) + 1;
            article.points = html.substring(start, end);
            // user
            start = html.indexOf(userStart, end) + userStart.length();
            end = html.indexOf(quote, start);
            article.user = html.substring(start, end);
        }
        // time
        start = html.indexOf(linkEnd, end + 1) + linkEnd.length();
        end = html.indexOf(job ? '<' : timeEnd, start);
        article.time = html.substring(start, end);

        if (!job) {
            // Comments
            end = html.indexOf(commentsEnd, end + 1);
            if (end == -1) {
                article.commentCount = "0";
            } else {
                start = html.lastIndexOf(endTag, end) + 1;
                article.commentCount = html.substring(start, end);
            }
        }

        articles.add(article);
    }

    public boolean hasMore() {
        return hasMore;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
