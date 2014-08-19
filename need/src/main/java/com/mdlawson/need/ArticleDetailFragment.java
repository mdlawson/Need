package com.mdlawson.need;

import android.os.Bundle;
import android.app.Fragment;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mdlawson.need.article.Article;
import com.mdlawson.need.events.ShowArticle;
import de.greenrobot.event.EventBus;


/**
 * A fragment representing a single Article detail screen.
 * This fragment is either contained in a {@link ArticleListActivity}
 * in two-pane mode (on tablets) or a {@link ArticleDetailActivity}
 * on handsets.
 */
public class ArticleDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ARTICLE = "article";

    /**
     * The dummy content this fragment is presenting.
     */
    private Article mArticle;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
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
        View rootView = inflater.inflate(R.layout.fragment_article_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mArticle != null) {
            ((TextView) rootView.findViewById(R.id.article_detail)).setText(mArticle.getTitle());
        }

        return rootView;
    }
}
