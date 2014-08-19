package com.mdlawson.need;

import android.os.Bundle;
import android.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import com.mdlawson.need.article.ArticleListAdapter;
import com.mdlawson.need.article.Section;
import com.mdlawson.need.events.SelectSource;
import com.mdlawson.need.events.ShowArticle;
import de.greenrobot.event.EventBus;


public class ArticleListFragment extends ListFragment {

    private static final String ARG_SECTION = "section";

    private int mActivatedPosition = ListView.INVALID_POSITION;

    public ArticleListFragment() {
    }

    private ArticleListAdapter mAdapter;
    private Section mSection;

    public static ArticleListFragment create(int section) {
        ArticleListFragment fragment = new ArticleListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION, section);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int section = getArguments().getInt(ARG_SECTION);

        mSection = EventBus.getDefault().getStickyEvent(SelectSource.class).getSource().getSections().get(section);

        mAdapter = new ArticleListAdapter(getActivity());
        mAdapter.updateArticles(mSection.getArticles());
        setListAdapter(mAdapter);

    }


    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        EventBus.getDefault().postSticky(new ShowArticle(mSection.getArticles().get(position)));
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) { // FIXME
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }
}
