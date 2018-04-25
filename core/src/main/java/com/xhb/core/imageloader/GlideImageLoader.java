package com.xhb.core.imageloader;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.Util;

/**
 * Author: Mr.xiao on 2017/3/11
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 使用Glide图片加载框架
 */

public class GlideImageLoader implements ImageLoader {

    @Override
    public void loadImage(String imageUrl, ImageView imageView, int placeholder, int errorImage) {
        if (!Util.isOnMainThread()) {
            return;
        }
        DrawableRequestBuilder builder = Glide.with(imageView.getContext()).load(imageUrl);
        if (placeholder != 0) {
            builder.placeholder(placeholder);
        }
        if (errorImage != 0) {
            builder.error(errorImage);
        }
        builder.dontAnimate();
        builder.into(imageView);

    }

    @Override
    public void loadCircleImage(String imageUrl, ImageView imageView, int placeholder, int errorImage) {
        if (!Util.isOnMainThread()) {
            return;
        }
        DrawableRequestBuilder builder = Glide.with(imageView.getContext()).load(imageUrl);
        if (placeholder != 0) {
            builder.placeholder(placeholder);
        }
        if (errorImage != 0) {
            builder.error(errorImage);
        }
        builder.bitmapTransform(new GlideCircleTransform(imageView.getContext()));
        builder.crossFade();
        builder.into(imageView);
    }

    @Override
    public void loadCornersImage(String imageUrl, ImageView imageView, int radius, int placeholder, int errorImage) {
        if (!Util.isOnMainThread()) {
            return;
        }
        DrawableRequestBuilder builder = Glide.with(imageView.getContext()).load(imageUrl);
        if (placeholder != 0) {
            builder.placeholder(placeholder);
        }
        if (errorImage != 0) {
            builder.error(errorImage);
        }
        builder.bitmapTransform(new CornersTransform(imageView.getContext(), radius));
        builder.crossFade();
        builder.into(imageView);
    }

    @Override
    public void loadIntoUseFitWidth(final ImageView imageView, String imageUrl) {
        if (!Util.isOnMainThread()) {
            return;
        }
        DrawableRequestBuilder builder = Glide.with(imageView.getContext()).load(imageUrl);

        builder.listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                if (imageView == null) {
                    return false;
                }
                if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                }
                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                float scale = (float) vw / (float) resource.getIntrinsicWidth();
                int vh = Math.round(resource.getIntrinsicHeight() * scale);
                params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                imageView.setLayoutParams(params);
                return false;
            }
        });
        builder.crossFade();
        builder.into(imageView);
    }
}
