package com.stx.xhb.enjoylife.ui.activity;

import android.support.v4.view.ViewPager;

import com.android.core.ui.BaseActivity;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.ui.adapter.PhotoViewPagerAdapter;

import java.util.ArrayList;

import butterknife.Bind;

public class PhotoViewActivity extends BaseActivity {

    @Bind(R.id.photo_viewpager)
    ViewPager photoViewpager;
    private PhotoViewPagerAdapter adapter;
    private ArrayList<String> imageList;
    private int mPos;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_photo_view;
    }

    @Override
    protected void onInitView() {
        photoViewpager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
        initData();
        setAdapter();
    }

    private void initData() {
        imageList = getIntent().getStringArrayListExtra("image");
        mPos = getIntent().getIntExtra("pos", 0);
    }

    private void setAdapter() {
        adapter = new PhotoViewPagerAdapter(this, imageList);
        photoViewpager.setAdapter(adapter);
        photoViewpager.setCurrentItem(mPos);
    }
}
