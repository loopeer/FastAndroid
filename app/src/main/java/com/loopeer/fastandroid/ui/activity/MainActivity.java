package com.loopeer.fastandroid.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.loopeer.fastandroid.ui.widget.FragmentTabHostExtend;
import com.loopeer.fastandroid.R;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements FragmentTabHostExtend.OnTabChangeListenerExtend {

    @Bind(android.R.id.tabhost)
    FragmentTabHostExtend mTabHost;

    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActionBar = getSupportActionBar();
        setupTabHost();
    }

    private void setupTabHost() {
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(createTab("0", R.string.home_title_0, android.R.drawable.ic_media_rew), Fragment.class, null);
        mTabHost.addTab(createTab("1", R.string.home_title_1, android.R.drawable.ic_media_previous), Fragment.class, null);
        mTabHost.addTab(createTab("2", R.string.home_title_2, android.R.drawable.ic_media_next), null, null);
        mTabHost.addTab(createTab("3", R.string.home_title_3, android.R.drawable.ic_media_ff), Fragment.class, null);

        mTabHost.setOnTabChangedListener(this);
    }

    private TabHost.TabSpec createTab(String tag, @StringRes int titleRes, @DrawableRes int iconRes) {
        View indicator = getLayoutInflater().inflate(R.layout.tab_indicator,
                mTabHost.getTabWidget(), false);

        ImageView icon = (ImageView) indicator.findViewById(android.R.id.icon);
        TextView title = (TextView) indicator.findViewById(android.R.id.title);

        Drawable iconBg = DrawableCompat.wrap(ContextCompat.getDrawable(this, iconRes));
        DrawableCompat.setTintList(iconBg, ContextCompat.getColorStateList(this, R.color.tab_indicator));
        icon.setImageDrawable(iconBg);
        title.setText(titleRes);

        return mTabHost.newTabSpec(tag).setIndicator(indicator);
    }

    @Override
    public boolean onTabChanged(int index) {
        boolean showActionBar = true;
        switch (index) {
            case 2:
                Toast.makeText(this, "Oops...", Toast.LENGTH_SHORT).show();
                return true;
            case 3:
                showActionBar = false;
                break;
        }
        if (showActionBar) {
            mActionBar.show();
        } else {
            mActionBar.hide();
        }
        return false;
    }
}
