package com.mdlawson.need.article.hn;

import android.util.Log;
import com.mdlawson.need.article.ArticleSource;
import com.mdlawson.need.article.Section;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class HNSection extends Section {

    private String url;
    private String name;

    public HNSection(String name, String url) {
        super();

        this.name = name;
        this.url = url;
    }

    @Override
    public void get(int page) {
        Request request = new Request.Builder()
                .url(url + "?page=" + page)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("HN", "fuck");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                HNPage page = new HNPage(response.body().string());
                yield(page.getArticles());
                if (page.hasMore()) hasMore();
            }
        });
    }

    @Override
    public String getName() {
        return name;
    }


}
