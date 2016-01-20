package com.loopeer.fastandroid.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.loopeer.fastandroid.R;
import com.loopeer.fastandroid.ui.adapter.ViewPagerAdapter;
import com.loopeer.fastandroid.ui.fragment.TestListFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PagerTestActivity extends AppCompatActivity {

    @Bind(R.id.tabs_pager_test)
    TabLayout mTabs;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    protected ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
    }

    protected void initView() {
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(TestListFragment.newInstance(), "tab1");
        mAdapter.addFragment(TestListFragment.newInstance(), "tab1");
        mAdapter.addFragment(TestListFragment.newInstance(), "tab1");
        mViewpager.setAdapter(mAdapter);
        mTabs.setupWithViewPager(mViewpager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
