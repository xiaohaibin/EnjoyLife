/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.stx.xhb.enjoylife.ui.adapter;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.config.GlideApp;
import com.stx.xhb.enjoylife.model.entity.TuChongWallPaperResponse;
import com.stx.xhb.enjoylife.ui.widget.RatioImageView;
import com.xhb.core.util.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

public class TuChongWallPaperAdapter extends BaseQuickAdapter<TuChongWallPaperResponse.FeedListBean, BaseViewHolder> {

    private OnImageItemClickListener mOnImageItemClickListener;

    public TuChongWallPaperAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder holder, TuChongWallPaperResponse.FeedListBean feedListBean) {
        TuChongWallPaperResponse.FeedListBean.EntryBean feedListBeanEntry = feedListBean.getEntry();
        if ("video".equals(feedListBeanEntry.getType())) {
            return;
        }
        final ImageView imageView = holder.getView(R.id.iv_img);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ScreenUtil.getScreenWidth(imageView.getContext()) / 3, ScreenUtil.getScreenWidth(imageView.getContext()) / 3 * 2);
        layoutParams.setMargins(2,2,2,2);
        imageView.setLayoutParams(layoutParams);
        List<TuChongWallPaperResponse.FeedListBean.EntryBean.ImagesBean> images = feedListBeanEntry.getImages();
        if (images == null || images.isEmpty()) {
            return;
        }
        TuChongWallPaperResponse.FeedListBean.EntryBean.ImagesBean imagesBean = images.get(0);
        if (imagesBean != null) {
            String url = "https://photo.tuchong.com/" + imagesBean.getUser_id() + "/f/" + imagesBean.getImg_id() + ".jpg";
            GlideApp.with(imageView.getContext())
                    .load(url)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade(500))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
            holder.itemView.setTag(url);
            ViewCompat.setTransitionName(imageView, url);
        }

        final ArrayList<String> imageList = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            TuChongWallPaperResponse.FeedListBean.EntryBean.ImagesBean imageData = images.get(i);
            String url = "https://photo.tuchong.com/" + imageData.getUser_id() + "/f/" + imageData.getImg_id() + ".jpg";
            imageList.add(url);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnImageItemClickListener != null) {
                    mOnImageItemClickListener.setOnImageClick(imageView, imageList);
                }
            }
        });
    }

    public void setOnImageItemClickListener(TuChongWallPaperAdapter.OnImageItemClickListener onImageItemClickListener) {
        mOnImageItemClickListener = onImageItemClickListener;
    }

    public interface OnImageItemClickListener {
        void setOnImageClick(View view, ArrayList<String> imageList);
    }
}
