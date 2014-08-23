package com.mdlawson.need.article;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.mdlawson.need.R;

import java.util.Collections;
import java.util.List;

public class ArticleListAdapter extends BaseAdapter {

    private List<Article> articles = Collections.emptyList();
    private final Context context;

    public ArticleListAdapter(Context context) {
        this.context = context;
    }
    public void updateArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Article getItem(int position) {
        return articles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.article_list_item, parent, false);
        }
        Article article = getItem(position);
        ((TextView) convertView).setText(article.getTitle());

        return convertView;
    }
}
