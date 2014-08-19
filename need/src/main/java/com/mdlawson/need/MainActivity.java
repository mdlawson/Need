package com.mdlawson.need;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.mdlawson.need.article.Article;
import com.mdlawson.need.article.ArticleSource;
import com.mdlawson.need.article.SourceListAdapter;
import com.mdlawson.need.article.dummy.DummySource;
import com.mdlawson.need.events.SelectSource;
import com.mdlawson.need.events.ShowArticle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements ListView.OnItemClickListener {

    private static final String STATE_TITLE = "title";

    private List<ArticleSource> mSources;

    private boolean mTwoPane;
    private ActionBarDrawerToggle mDrawerToggle;
    @InjectView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @InjectView(R.id.left_drawer) ListView mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MAIN", "onCreate()");
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
        bus.register(this);

        final CharSequence title = getTitle();

        if (findViewById(R.id.article_detail_container) != null) {
            mTwoPane = true;

//            // In two-pane mode, list items should be given the FIXME
//            // 'activated' state when touched.
//            ((ArticleListFragment) getFragmentManager()
//                    .findFragmentById(R.id.article_list))
//                    .setActivateOnItemClick(true);
        }

        final ActionBar actionBar = getActionBar();

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_navigation_drawer,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                actionBar.setTitle(R.string.drawer_title);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                actionBar.setTitle(title);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        Need need = (Need) getApplication();
        mSources = need.getSources();

        mDrawer.setAdapter(new SourceListAdapter(this, mSources));
        mDrawer.setOnItemClickListener(this);

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        SelectSource selected = bus.getStickyEvent(SelectSource.class);

        if (selected != null) {
            displaySource(selected);
        } else {
            mDrawerLayout.openDrawer(mDrawer);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putCharSequence(STATE_TITLE, getActionBar().getTitle());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(STATE_TITLE)) {
            getActionBar().setTitle(savedInstanceState.getCharSequence(STATE_TITLE));
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displaySource(SelectSource selectSource) {
        bus.postSticky(selectSource);
        SectionsFragment fragment = new SectionsFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.article_list_container, fragment)
                .commit();
    }

    /**
     * Draw menu item click
     */
    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        ArticleSource source = mSources.get(position);

        displaySource(new SelectSource(source));

        mDrawerLayout.closeDrawers();
    }

    public void onEvent(ShowArticle showArticle) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            ArticleDetailFragment fragment = new ArticleDetailFragment();
            getFragmentManager().beginTransaction()
                    .replace(R.id.article_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, ArticleDetailActivity.class);
            startActivity(detailIntent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MAIN", "onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MAIN", "onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MAIN", "onResume()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MAIN", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MAIN", "onDestroy()");
    }
}