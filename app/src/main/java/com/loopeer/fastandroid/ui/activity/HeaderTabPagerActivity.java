package com.loopeer.fastandroid.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.loopeer.fastandroid.R;
import com.loopeer.fastandroid.ui.adapter.ViewPagerAdapter;
import com.loopeer.fastandroid.ui.fragment.TestListFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HeaderTabPagerActivity extends AppCompatActivity {

    @Bind(R.id.viewpager) ViewPager viewPager;
    @Bind(R.id.toolbar)  Toolbar toolbar;
    @Bind(R.id.tabs_header_pager)  TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_tab_pager);

        ButterKnife.bind(this);
        initToolbar();
        setUpView();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setUpView() {
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(TestListFragment.newInstance(), "Category 1");
        adapter.addFragment(TestListFragment.newInstance(), "Category 2");
        adapter.addFragment(TestListFragment.newInstance(), "Category 3");
        viewPager.setAdapter(adapter);

    }
}
