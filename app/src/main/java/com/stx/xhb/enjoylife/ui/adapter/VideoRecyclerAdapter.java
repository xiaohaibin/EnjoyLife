package com.stx.xhb.enjoylife.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.model.entity.VideoEntity;

import java.util.List;

/**
 * @author: xiaohaibin.
 * @time: 2018/4/26
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class VideoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BANNER = 0;
    private static final int VIDEO = 1;
    private static final int TEXT = 2;
    private Context mContext;
    private List<VideoEntity.IssueListEntity.ItemListEntity> mItemList;

    public VideoRecyclerAdapter(Context context, List<VideoEntity.IssueListEntity.ItemListEntity> itemList) {
        mContext = context;
        mItemList = itemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BANNER:
                return new BannerViewHodler(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_home_item_banner, parent, false));
            case TEXT:
                return new TextViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_home_text_item, parent, false));
            case VIDEO:
                return new VideoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_home_vedio_item, parent, false));
            default:
                return new VideoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_home_vedio_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        VideoEntity.IssueListEntity.ItemListEntity itemListEntity = mItemList.get(position);
        if (holder instanceof BannerViewHodler) {
            Glide.with(mContext)
                    .load(itemListEntity.getData().getImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(((BannerViewHodler) holder).ivBanner);
        } else if (holder instanceof TextViewHolder) {
            if (itemListEntity.getType().startsWith("banner")) {
                ((TextViewHolder) holder).tvTitle.setVisibility(View.GONE);
            } else {
                ((TextViewHolder) holder).tvTitle.setVisibility(View.VISIBLE);
            }
            String image = itemListEntity.getData().getImage();
            if (!TextUtils.isEmpty(image)) {
                ((TextViewHolder) holder).tvTitle.setTextSize(20);
                ((TextViewHolder) holder).tvTitle.setText("-Weekend  special-");
            } else {
                ((TextViewHolder) holder).tvTitle.setText(itemListEntity.getData().getText());
            }
        }else if (holder instanceof VideoViewHolder){
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

            Glide.with(mContext).load(feed).diskCacheStrategy(DiskCacheStrategy.ALL).into(((VideoViewHolder) holder).imageView);
            ((VideoViewHolder) holder).tvTitle.setText(title);
            ((VideoViewHolder) holder).tvTime.setText(String.valueOf(category + stringTime));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener!=null){
                        mItemClickListener.onItemClick(((VideoViewHolder) holder).imageView,position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        VideoEntity.IssueListEntity.ItemListEntity itemListEntity = mItemList.get(position);
        if ("video".equals(itemListEntity.getType())) {
            return VIDEO;
        } else if (itemListEntity.getType().startsWith("banner") && TextUtils.isEmpty(itemListEntity.getData().getActionUrl())) {
            return BANNER;
        }
        return TEXT;
    }

    class BannerViewHodler extends RecyclerView.ViewHolder {
        ImageView ivBanner;

        BannerViewHodler(View itemView) {
            super(itemView);
            ivBanner = (ImageView) itemView.findViewById(R.id.iv_banner);
        }
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        TextViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_home_text);
        }
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvTitle;
        TextView tvTime;

        VideoViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }

    private setOnItemClickListener mItemClickListener;

    public void setItemClickListener(setOnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface setOnItemClickListener{
        void onItemClick(View view,int pos);
    }
}
