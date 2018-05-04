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

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.model.entity.TuChongWallPaperResponse;
import com.stx.xhb.enjoylife.model.entity.TuchongImagResponse;
import com.stx.xhb.enjoylife.ui.widget.RatioImageView;
import com.xhb.core.adapter.RecyclerAdapter;
import com.xhb.core.adapter.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TuChongWallPaperAdapter extends RecyclerAdapter<TuChongWallPaperResponse.FeedListBean> {

    public static final String TAG = "TuChongListAdapter";

    private Context mContext;
    private OnImageItemClickListener mOnImageItemClickListener;

    public TuChongWallPaperAdapter(Context context, int layoutId, List<TuChongWallPaperResponse.FeedListBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
    }

    @Override
    public void convert(final RecyclerViewHolder holder, TuChongWallPaperResponse.FeedListBean feedListBean) {
        TuChongWallPaperResponse.FeedListBean.EntryBean feedListBeanEntry = feedListBean.getEntry();
        if ("video".equals(feedListBeanEntry.getType())) {
            return;
        }
        final RatioImageView imageView = (RatioImageView) holder.getView(R.id.iv_img);
        imageView.setOriginalSize(50, 50);
        int limit = 48;
        String text = feedListBeanEntry.getTitle().length() > limit ? feedListBeanEntry.getTitle().substring(0, limit) +
                "..." : feedListBeanEntry.getTitle();
        ((TextView) holder.getView(R.id.tv_title)).setText(text);

        List<TuChongWallPaperResponse.FeedListBean.EntryBean.ImagesBean> images = feedListBeanEntry.getImages();
        if (images == null || images.isEmpty()) {
            return;
        }
        TuChongWallPaperResponse.FeedListBean.EntryBean.ImagesBean imagesBean = images.get(0);
        if (imagesBean != null) {
            String url = "https://photo.tuchong.com/" + imagesBean.getUser_id() + "/f/" + imagesBean.getImg_id() + ".jpg";
            Glide.with(mContext)
                    .load(url)
                    .centerCrop()
                    .into(imageView)
                    .getSize(new SizeReadyCallback() {
                        @Override
                        public void onSizeReady(int width, int height) {
                            if (!holder.itemView.isShown()) {
                                holder.itemView.setVisibility(View.VISIBLE);
                            }
                        }
                    });
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

    public void setOnImageItemClickListener(OnImageItemClickListener onImageItemClickListener) {
        mOnImageItemClickListener = onImageItemClickListener;
    }

    public interface OnImageItemClickListener {
        void setOnImageClick(View view, ArrayList<String> imageList);
    }
}
