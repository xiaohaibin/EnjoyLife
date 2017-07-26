package com.stx.xhb.enjoylife.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.jaeger.library.StatusBarUtil;
import com.meikoz.core.base.BaseActivity;
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
    protected void onInitialization(Bundle bundle) {
        StatusBarUtil.setTranslucent(this);
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
        adapter.setOnClickListener(new PhotoViewPagerAdapter.onImageLayoutOnClickListener() {
            @Override
            public void OnImageOnClik() {
                finish();
            }
        });
    }
}
