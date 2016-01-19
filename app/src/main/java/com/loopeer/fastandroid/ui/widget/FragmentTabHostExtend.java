package com.loopeer.fastandroid.ui.widget;

import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.util.AttributeSet;

public class FragmentTabHostExtend extends FragmentTabHost {

    private OnTabChangeListenerExtend mOnTabChangeListener;

    public FragmentTabHostExtend(Context context) {
        super(context);
    }

    public FragmentTabHostExtend(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnTabChangedListener(OnTabChangeListenerExtend l) {
        mOnTabChangeListener = l;
    }

    @Override
    public void setCurrentTab(int index) {
        if (mOnTabChangeListener != null && mOnTabChangeListener.onTabChanged(index)) {
            return;
        }

        super.setCurrentTab(index);
    }

    public interface OnTabChangeListenerExtend {
        boolean onTabChanged(int index);
    }
}
