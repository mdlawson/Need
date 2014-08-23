package com.mdlawson.need;



import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.mdlawson.need.article.Article;
import com.mdlawson.need.article.ArticleSource;
import com.mdlawson.need.article.Section;
import com.mdlawson.need.events.SelectSource;
import com.mdlawson.need.view.SlidingTabLayout;
import de.greenrobot.event.EventBus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SectionsFragment extends Fragment {

    public static final String ARG_SOURCE = "source";

    public static final String STATE_PAGER = "pager";

    @InjectView(R.id.pager) ViewPager mPager;
    @InjectView(R.id.sliding_tabs) SlidingTabLayout mTabs;

    ArticleSource mSource;

    public SectionsFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sections, container, false);
        ButterKnife.inject(this, view);
        mSource = EventBus.getDefault().getStickyEvent(SelectSource.class).getSource();

        for (Section section : mSource.getSections()) {
            section.reload();
        }

        getActivity().getActionBar().setBackgroundDrawable(new ColorDrawable(mSource.getColor()));

        mPager.setAdapter(new SectionsAdapter(getChildFragmentManager(), mSource.getSections()));

        mTabs.setViewPager(mPager);
        mTabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return mSource.getColor();
            }

            @Override
            public int getDividerColor(int position) {
                return 0x00FFFFFF;
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(STATE_PAGER, mPager.onSaveInstanceState());
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.containsKey(STATE_PAGER)) {
            mPager.onRestoreInstanceState(savedInstanceState.getParcelable(STATE_PAGER));
        }
    }

    public static class SectionsAdapter extends FragmentPagerAdapter {

        List<Section> sections;

        public SectionsAdapter(FragmentManager fm, List<Section> sections) {
            super(fm);

            this.sections = sections;
        }

        @Override
        public Fragment getItem(int position) {
            return ArticleListFragment.create(position);
        }

        @Override
        public int getCount() {
            return sections.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return sections.get(position).getName();
        }
    }


}
