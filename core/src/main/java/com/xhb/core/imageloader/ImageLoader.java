package com.xhb.core.imageloader;

import android.widget.ImageView;

/**
 * @Author: Mr.xiao on 2017/3/11
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 图片加载功能接口，添加或者替换新的图片加载器实现该接口即可
 */

public interface ImageLoader {

    /**
     * 展示普通的图片
     *
     * @param imageUrl    图片地址
     * @param imageView   image控件
     * @param placeholder 占位图
     * @param errorImage  失败占位图
     */
    void loadImage(String imageUrl, ImageView imageView, int placeholder, int errorImage);

    /**
     * 展示圆形图片
     *
     * @param imageUrl    图片地址
     * @param imageView   image控件
     * @param placeholder 占位图
     * @param errorImage  失败占位图
     */
    void loadCircleImage(String imageUrl, ImageView imageView, int placeholder, int errorImage);

    /**
     * 展示圆角图片
     *
     * @param imageUrl    图片地址
     * @param imageView   image控件
     * @param radius      圆角半径
     * @param placeholder 占位图
     * @param errorImage  失败占位图
     */
    void loadCornersImage(String imageUrl, ImageView imageView, int radius, int placeholder, int errorImage);

    /**
     *
     * 自适应宽度加载图片。保持图片的长宽比例不变，通过修改imageView的高度来完全显示图片,不需要设置占位图，否则会出现图片第一次加载出现模糊
     * @param imageView
     * @param imageUrl
     */
    void loadIntoUseFitWidth(ImageView imageView, String imageUrl);
}
