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

public class SourceListAdapter extends BaseAdapter {

    private List<ArticleSource> sources = Collections.emptyList();
    private final Context context;

    public SourceListAdapter(Context context, List<ArticleSource> sources) {
        this.context = context;
        this.sources = sources;
    }

    @Override
    public int getCount() {
        return sources.size();
    }

    @Override
    public ArticleSource getItem(int position) {
        return sources.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.drawer_list_item, parent, false);
        }
        ArticleSource source = getItem(position);
        ((TextView) convertView).setText(source.getName());

        return convertView;
    }
}
