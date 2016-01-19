package com.loopeer.fastandroid.ui.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

import com.loopeer.fastandroid.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Nullable
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Nullable
    @Bind(R.id.toolbar_title)
    TextView mToolbarTitle;

    private boolean mUpAsFinish;

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

        if (mToolbar != null) {
            onSetupToolbar(mToolbar);
        }

        String title = getIntent().getStringExtra(Intent.EXTRA_TITLE);
        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
        }
    }

    protected void onSetupToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        try {
            if (NavUtils.getParentActivityName(this, getComponentName()) != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        } catch (PackageManager.NameNotFoundException e) {
            // ignore
        }
        if (mToolbarTitle != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        if (mToolbarTitle != null) {
            mToolbarTitle.setText(title);
        } else if (mToolbar != null) {
            mToolbar.setTitle(title);
        }
    }

    public void setUpAsFinish(boolean homeAsFinish) {
        if (getSupportActionBar() == null) {
            return;
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsFinish);
        mUpAsFinish = homeAsFinish;
    }

    @Override
    public boolean onNavigateUp() {
        if (mUpAsFinish) {
            finish();
            return true;
        } else {
            return super.onNavigateUp();
        }
    }
}
