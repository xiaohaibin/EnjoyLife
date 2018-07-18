package com.stx.xhb.enjoylife.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.github.chrisbanes.photoview.OnViewTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.stx.xhb.enjoylife.config.GlideApp;

import java.util.ArrayList;


/**
 * Created by Mr.xiao on 16/8/26.
 * 图片预览Viewpager适配器
 */
public class PhotoViewPagerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<String> imageList;
    private onImageLayoutListener mImageLayoutListener;

    public PhotoViewPagerAdapter(Context context, ArrayList<String> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final PhotoView photoView = new PhotoView(context);
        photoView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        final String imgUrl = imageList.get(position);
        GlideApp.with(context)
                .load(imgUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(photoView);

        photoView.setOnViewTapListener(new OnViewTapListener() {
            @Override
            public void onViewTap(View view, float v, float v1) {
                if (mImageLayoutListener != null) {
                    mImageLayoutListener.setOnImageOnClik();
                }
            }
        });

        photoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mImageLayoutListener != null) {
                    mImageLayoutListener.setLongClick(imgUrl);
                }
                return true;
            }
        });
        container.addView(photoView);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setOnClickListener(onImageLayoutListener onClickListener) {
        mImageLayoutListener = onClickListener;
    }

    public interface onImageLayoutListener {

        void setOnImageOnClik();

        void setLongClick(String url);

    }
}
