package com.loopeer.fastandroid.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.loopeer.fastandroid.event.SplashDisplayEvent;

import de.greenrobot.event.EventBus;

public class SplashActivity extends BaseActivity {

    public static final int SPLASH_DISPLAY_MILLIS = 2000;

    private Handler mHandler;
    private Runnable mDoneRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUi();

        if (WizardActivity.shouldDisplay(this)) {
            startActivityAndFinish(WizardActivity.class);
            return;
        }

        EventBus eventBus = EventBus.getDefault();
        if (eventBus.getStickyEvent(SplashDisplayEvent.class) != null) {
            eventBus.removeStickyEvent(SplashDisplayEvent.class);
            mHandler = new Handler();
            mDoneRunnable = new Runnable() {
                @Override
                public void run() {
                    startActivityAndFinish(MainActivity.class);
                }
            };
            mHandler.postDelayed(mDoneRunnable, SPLASH_DISPLAY_MILLIS);
        } else {
            startActivityAndFinish(MainActivity.class);
        }
    }

    private void startActivityAndFinish(Class<?> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mHandler.removeCallbacks(mDoneRunnable);
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }
}
