package com.xhb.core.imageloader;

import android.widget.ImageView;

/**
 * @Author: Mr.xiao on 2017/3/12
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 图片加载代理类，所有的图片加载操作都通过该代理类去实现；
 * 如果要改变图片加载框架，只需要在该类里替换相应的图片加载框架即可，客户端所有引用的图片操作都不需要修改
 */

public class ImageLoaderProxy implements ImageLoader {

    private ImageLoader mImageLoader;//代理对象

    private static ImageLoaderProxy sImageLoaderProxy;

    public ImageLoaderProxy() {
        mImageLoader = new GlideImageLoader();
    }

    public static ImageLoaderProxy getInstance() {
        if (sImageLoaderProxy == null) {
            synchronized (ImageLoaderProxy.class) {
                if (sImageLoaderProxy == null) {
                    sImageLoaderProxy = new ImageLoaderProxy();
                }
            }
        }
        return sImageLoaderProxy;
    }


    @Override
    public void loadImage(String imageUrl, ImageView imageView, int placeholder, int errorImage) {
        mImageLoader.loadImage(imageUrl, imageView, placeholder, errorImage);
    }

    @Override
    public void loadCircleImage(String imageUrl, ImageView imageView, int placeholder, int errorImage) {
        mImageLoader.loadCircleImage(imageUrl, imageView, placeholder, errorImage);
    }

    @Override
    public void loadCornersImage(String imageUrl, ImageView imageView, int radius, int placeholder, int errorImage) {
        mImageLoader.loadCornersImage(imageUrl, imageView, radius, placeholder, errorImage);
    }

    @Override
    public void loadIntoUseFitWidth(ImageView imageView, String imageUrl) {
        mImageLoader.loadIntoUseFitWidth(imageView, imageUrl);
    }
}
