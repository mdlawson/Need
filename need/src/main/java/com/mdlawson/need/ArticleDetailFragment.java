package com.mdlawson.need;

import android.os.Bundle;
import android.app.Fragment;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.mdlawson.need.article.Article;
import com.mdlawson.need.events.ShowArticle;
import de.greenrobot.event.EventBus;


public class ArticleDetailFragment extends Fragment {

    public static final String ARG_ARTICLE = "article";

    private Article mArticle;

    @InjectView(R.id.article_title) TextView mTitle;
    @InjectView(R.id.article_subtitle) TextView mSubtitle;

    public ArticleDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mArticle = EventBus.getDefault().getStickyEvent(ShowArticle.class).getArticle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_detail, container, false);
        ButterKnife.inject(this, view);

        mTitle.setText(mArticle.getTitle());
        mSubtitle.setText("Subtitle");

        return view;
    }
}
