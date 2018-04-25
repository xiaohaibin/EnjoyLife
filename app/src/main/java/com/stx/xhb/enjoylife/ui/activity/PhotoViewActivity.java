package com.stx.xhb.enjoylife.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.xhb.core.base.BaseActivity;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.ui.adapter.PhotoViewPagerAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhotoViewActivity extends BaseActivity {

    @Bind(R.id.photo_viewpager)
    ViewPager photoViewpager;
    @Bind(R.id.tv_indicator)
    TextView mTvIndicator;
    private ArrayList<String> imageList;
    private int mPos;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_photo_view;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        StatusBarUtil.setTranslucent(this);
        photoViewpager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
        photoViewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mTvIndicator.setText(String.valueOf((photoViewpager.getCurrentItem() + 1) + "/" + imageList.size()));
            }
        });
        initData();
        setAdapter();
    }

    private void initData() {
        imageList = getIntent().getStringArrayListExtra("image");
        mPos = getIntent().getIntExtra("pos", 0);
        mTvIndicator.setText(String.valueOf((mPos + 1) + "/" + imageList.size()));
    }

    private void setAdapter() {
        PhotoViewPagerAdapter adapter = new PhotoViewPagerAdapter(this, imageList);
        photoViewpager.setAdapter(adapter);
        photoViewpager.setCurrentItem(mPos);
        adapter.setOnClickListener(new PhotoViewPagerAdapter.onImageLayoutOnClickListener() {
            @Override
            public void OnImageOnClik() {
                finish();
            }
        });
    }

}
