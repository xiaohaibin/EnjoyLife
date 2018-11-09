package com.stx.xhb.enjoylife.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.OnViewTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.config.GlideApp;
import com.stx.xhb.enjoylife.utils.ToastUtil;

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
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View convertView = View.inflate(context, R.layout.item_photoview, null);
        final PhotoView photoView = convertView.findViewById(R.id.photo_view);
        final ProgressBar progressBar = convertView.findViewById(R.id.progress_view);
        final String imgUrl = imageList.get(position);

        photoView.setOnViewTapListener(new OnViewTapListener() {
            @Override
            public void onViewTap(View view, float v, float v1) {
                if (mImageLayoutListener != null) {
                    mImageLayoutListener.setOnImageOnClik();
                }
            }
        });
        GlideApp.with(context)
                .load(imgUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        ToastUtil.show("图片加载失败");
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(photoView);

        photoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mImageLayoutListener != null) {
                    mImageLayoutListener.setLongClick(imgUrl);
                }
                return true;
            }
        });
        container.addView(convertView);
        return convertView;
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
