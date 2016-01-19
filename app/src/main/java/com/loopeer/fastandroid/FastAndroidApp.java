package com.loopeer.fastandroid;

import android.app.Application;

import com.loopeer.fastandroid.event.SplashDisplayEvent;

import de.greenrobot.event.EventBus;

public class FastAndroidApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().postSticky(new SplashDisplayEvent());
    }
}
