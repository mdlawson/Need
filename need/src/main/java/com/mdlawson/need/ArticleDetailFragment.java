package com.mdlawson.need;

import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.mdlawson.need.article.Article;
import com.mdlawson.need.article.CommentListAdapter;
import com.mdlawson.need.events.ArticleUpdated;
import com.mdlawson.need.events.ShowArticle;
import de.greenrobot.event.EventBus;


public class ArticleDetailFragment extends ListFragment {

    public static final String ARG_ARTICLE = "article";

    private Article mArticle;
    private CommentListAdapter mAdapter;

    @InjectView(R.id.article_title) TextView mTitle;
    @InjectView(R.id.article_subtitle) TextView mSubtitle;

    public ArticleDetailFragment() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mArticle = EventBus.getDefault().getStickyEvent(ShowArticle.class).getArticle();
        mArticle.getDetails();
        mAdapter = new CommentListAdapter(getActivity());
        setListAdapter(mAdapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View header = View.inflate(getActivity(), R.layout.article_detail_header, null);
        ButterKnife.inject(this, header);

        mTitle.setText(mArticle.getTitle());
        mSubtitle.setText("Subtitle");

        getListView().addHeaderView(header);
    }

    public void onEventMainThread(ArticleUpdated updated) {
        if (updated.getArticle() == mArticle) {
            mAdapter.setComments(mArticle.getComments());
            mAdapter.notifyDataSetChanged();
        }
    }
}
