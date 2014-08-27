package com.mdlawson.need.article.hn;

import android.util.Log;
import com.mdlawson.need.article.Article;
import com.mdlawson.need.article.Comment;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

public class HNArticle extends Article {

    enum Type {ASK, SHOW, JOB, SELF, LINK}


    String id;
    String title;
    String url;
    String points;
    String time;
    String user;
    String commentCount;
    Type type;
    List<Comment> comments;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void getDetails() {
        final Request request = new Request.Builder()
                .url(HNSource.url + "item?id=" + id)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("HN", "Fuck"); // TODO
            }

            @Override
            public void onResponse(Response response) throws IOException {
                HNCommentsPage commentsPage = new HNCommentsPage(response.body().string());
                comments = commentsPage.getComments();
                notifyUpdated();
            }
        });
    }

    @Override
    public List<Comment> getComments() {
        return comments;
    }
}
