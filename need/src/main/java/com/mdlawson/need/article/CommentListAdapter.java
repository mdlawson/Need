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

public class CommentListAdapter extends BaseAdapter {

    private List<Comment> comments = Collections.emptyList();
    private final Context context;

    public CommentListAdapter(Context context) {
        this.context = context;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Comment getItem(int position) {
        return comments.get(position);
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
        Comment comment = getItem(position);
        ((TextView) convertView).setText(comment.getText());

        return convertView;
    }
}
