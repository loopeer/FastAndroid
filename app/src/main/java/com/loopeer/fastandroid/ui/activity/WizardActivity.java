package com.loopeer.fastandroid.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Matrix;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.loopeer.fastandroid.R;

import butterknife.Bind;
import butterknife.OnClick;

public class WizardActivity extends BaseActivity {

    private static final String PREF_DISPLAYED_WIZARD_VERSION = "pref_shown_wizard_version";

    private static final int CURRENT_VERSION = 0;

    public static final int[] PAGES = new int[]{
            // TODO put wizard pages in here.
    };

    public static boolean shouldDisplay(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return CURRENT_VERSION > sp.getInt(PREF_DISPLAYED_WIZARD_VERSION, 0);
    }

    @Bind(R.id.pager_wizard)
    ViewPager mPagerWizard;
    @Bind(R.id.btn_done)
    Button mBtnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard);

        final WizardAdapter adapter = new WizardAdapter();
        mPagerWizard.setAdapter(adapter);
        mPagerWizard.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == adapter.getCount() - 1) {
                    mBtnDone.animate().alpha(1);
                } else if (position == adapter.getCount() - 2) {
                    mBtnDone.animate().alpha(0);
                }
            }
        });

        mBtnDone.setAlpha(adapter.getCount() > 1 ? 0 : 1);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.btn_done)
    public void onDoneClick() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.edit().putInt(PREF_DISPLAYED_WIZARD_VERSION, CURRENT_VERSION).apply();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    class WizardAdapter extends PagerAdapter {

        private Matrix mImageMatrix;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = new ImageView(WizardActivity.this);
            view.setImageResource(PAGES[position]);
            if (mImageMatrix == null) {
                mImageMatrix = new Matrix();
                float scale = (float) container.getMeasuredWidth()
                        / (float) view.getDrawable().getIntrinsicWidth();
                mImageMatrix.setScale(scale, scale);
            }
            view.setScaleType(ImageView.ScaleType.MATRIX);
            view.setImageMatrix(mImageMatrix);
            container.addView(view,
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return PAGES.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
