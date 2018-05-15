package com.stx.xhb.enjoylife.ui.activity;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.ui.adapter.PhotoViewPagerAdapter;
import com.stx.xhb.enjoylife.utils.ShareUtils;
import com.stx.xhb.enjoylife.utils.ToastUtil;
import com.xhb.core.base.BaseActivity;
import com.xhb.core.util.RxImage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class PhotoViewActivity extends BaseActivity {

    public static final int PERMISS_REQUEST_CODE = 0x001;
    @Bind(R.id.photo_viewpager)
    ViewPager photoViewpager;
    @Bind(R.id.tv_indicator)
    TextView mTvIndicator;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private ArrayList<String> imageList;
    private int mPos;
    public static final String TRANSIT_PIC = "transit_img";
    private String saveImgUrl = "";

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_photo_view;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
        photoViewpager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
        photoViewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                saveImgUrl = imageList.get(photoViewpager.getCurrentItem());
                mTvIndicator.setText(String.valueOf((photoViewpager.getCurrentItem() + 1) + "/" + imageList.size()));
            }
        });
        ViewCompat.setTransitionName(photoViewpager, PhotoViewActivity.TRANSIT_PIC);
        initData();
        setAdapter();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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
        adapter.setOnClickListener(new PhotoViewPagerAdapter.onImageLayoutListener() {
            @Override
            public void setOnImageOnClik() {
                onBackPressed();
            }

            @Override
            public void setLongClick(String url) {
                new AlertDialog.Builder(PhotoViewActivity.this)
                        .setMessage(getString(R.string.ask_saving_picture))
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (checkPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})) {
                                    saveImage();
                                } else {
                                    requestPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISS_REQUEST_CODE);
                                }
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PERMISS_REQUEST_CODE == requestCode) {
            if (checkPermissions(permissions)) {
                saveImage();
            } else {
                showTipsDialog();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                if (checkPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})) {
                    saveImage();
                } else {
                    requestPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISS_REQUEST_CODE);
                }
                return true;
            case R.id.menu_setting_picture:
                setWallpaper();
                return true;
            case R.id.menu_share:
                Subscription subscribe = RxImage.saveImageAndGetPathObservable(this, saveImgUrl)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Uri>() {
                            @Override
                            public void call(Uri uri) {
                                ShareUtils.shareImage(PhotoViewActivity.this, uri, getString(R.string.share_image_to));
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                ToastUtil.show(throwable.getMessage());
                            }
                        });
                addSubscription(subscribe);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 保存图片
     */
    private void saveImage() {
        Subscription subscribe = RxImage.saveImageAndGetPathObservable(this, saveImgUrl)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Uri>() {
                    @Override
                    public void call(Uri uri) {
                        File appDir = new File(Environment.getExternalStorageDirectory(), "EnjoyLife");
                        String msg = String.format(getString(R.string.picture_has_save_to),
                                appDir.getAbsolutePath());
                        ToastUtil.show(msg);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        ToastUtil.show(getString(R.string.string_img_save_failed));
                    }
                });
        addSubscription(subscribe);
    }

    private void setWallpaper() {
        Glide.with(this).load(saveImgUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                WallpaperManager manager = WallpaperManager.getInstance(PhotoViewActivity.this);
                try {
                    manager.setBitmap(resource);
                    ToastUtil.show("设置壁纸成功");
                } catch (IOException e) {
                    e.printStackTrace();
                    ToastUtil.show("设置壁纸失败");
                }
            }
        });
    }
}
