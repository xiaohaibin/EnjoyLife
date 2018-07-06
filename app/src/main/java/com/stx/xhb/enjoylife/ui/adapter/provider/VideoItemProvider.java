package com.stx.xhb.enjoylife.ui.adapter.provider;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.config.GlideApp;
import com.stx.xhb.enjoylife.model.entity.VideoResponse;
import com.stx.xhb.enjoylife.ui.adapter.VideoRecyclerAdapter;

/**
 * @author: xiaohaibin.
 * @time: 2018/5/28
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class VideoItemProvider extends BaseItemProvider<VideoResponse.IssueListEntity.ItemListEntity, BaseViewHolder> {
    @Override
    public int viewType() {
        return VideoRecyclerAdapter.VIDEO;
    }

    @Override
    public int layout() {
        return R.layout.list_home_video_item;
    }

    @Override
    public void convert(BaseViewHolder holder,  VideoResponse.IssueListEntity.ItemListEntity itemListEntity, int i) {
        //得到不同类型所需要的数据
        String feed = itemListEntity.getData().getCover().getFeed();
        String title = itemListEntity.getData().getTitle();
        String category = itemListEntity.getData().getCategory();
        category = "#" + category + "  /  ";
        int duration = itemListEntity.getData().getDuration();

        int last = duration % 60;
        String stringLast;
        if (last <= 9) {
            stringLast = "0" + last;
        } else {
            stringLast = last + "";
        }

        String durationString;
        int minit = duration / 60;
        if (minit < 10) {
            durationString = "0" + minit;
        } else {
            durationString = "" + minit;
        }
        String stringTime = durationString + "' " + stringLast + '"';

        final ImageView view = holder.getView(R.id.iv);
        GlideApp.with(view.getContext()).load(feed).diskCacheStrategy(DiskCacheStrategy.ALL).transition(DrawableTransitionOptions.withCrossFade()).into(view);
        holder.setText(R.id.tv_title, title);
        holder.setText(R.id.tv_time, String.valueOf(category + stringTime));
    }

    @Override
    public void onClick(BaseViewHolder holder, VideoResponse.IssueListEntity.ItemListEntity data, int position) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick(holder.getView(R.id.iv), data);
        }
    }

    private setOnItemClickListener mItemClickListener;

    public void setItemClickListener(setOnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface setOnItemClickListener {
        void onItemClick(View view, VideoResponse.IssueListEntity.ItemListEntity data);
    }
}
