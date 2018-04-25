package com.stx.xhb.enjoylife.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.model.entity.VideoEntity;

import java.util.List;

/**
 * Created by ASUS on 2016/2/29.
 */
public class VideoRecycleAdapter extends BaseAdapter {
    private static final int BANNER = 0;
    private static final int VIDEO = 1;
    private static final int TEXT = 2;
    private Context mContext;
    private List<VideoEntity.IssueListEntity.ItemListEntity> mItemList;

    public VideoRecycleAdapter(Context mContext, List<VideoEntity.IssueListEntity.ItemListEntity> itemList) {
        this.mContext = mContext;
        this.mItemList = itemList;
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

    @Override
    public int getCount() {
        return mItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        VideoEntity.IssueListEntity.ItemListEntity itemListEntity = mItemList.get(position);

        int type = getItemViewType(position);

        String feed = "";
        String title = "";
        String category = "";
        int duration = 0;
        String text = "";

        ViewHolder holder = new ViewHolder();

        switch (type) {
            case BANNER:
                convertView = LayoutInflater.from(mContext).inflate(R.layout.list_home_item_banner, parent, false);
                ImageView ivBanner = (ImageView) convertView.findViewById(R.id.iv_banner);
                Glide.with(mContext)
                        .load(itemListEntity.getData().getImage())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(ivBanner);
                return convertView;

            case TEXT:
                convertView = LayoutInflater.from(mContext).inflate(R.layout.list_home_text_item, parent, false);
                TextView textView = (TextView) convertView.findViewById(R.id.tv_home_text);
                //set data
                if (itemListEntity.getType().startsWith("banner")) {
                    textView.setVisibility(View.GONE);
                } else {
                    textView.setVisibility(View.VISIBLE);
                }
                String image = itemListEntity.getData().getImage();
                if (!TextUtils.isEmpty(image)) {
                    textView.setTextSize(20);
                    textView.setText("-Weekend  special-");
                } else {
                    text = itemListEntity.getData().getText();
                    textView.setText(text);
                }
                return convertView;

            default:
                //得到不同类型所需要的数据
                feed = itemListEntity.getData().getCover().getFeed();
                title = itemListEntity.getData().getTitle();
                category = itemListEntity.getData().getCategory();
                category = "#" + category + "  /  ";

                duration = itemListEntity.getData().getDuration();

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

                //设置布局
                convertView = LayoutInflater.from(mContext).inflate(R.layout.list_home_vedio_item, parent, false);
                if (convertView == null) {
                    holder.imageView = (ImageView) convertView.findViewById(R.id.iv);
                    holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                    holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
                    convertView.setTag(holder);

                } else {
                    if (convertView.getTag() instanceof ViewHolder) {
                        holder = (ViewHolder) convertView.getTag();
                    } else {
                        holder.imageView = (ImageView) convertView.findViewById(R.id.iv);
                        holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                        holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
                        convertView.setTag(holder);
                    }
                }

                //set data
                Glide.with(mContext).load(feed).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
                holder.tvTitle.setText(title);
                holder.tvTime.setText(String.valueOf(category + stringTime));

                return convertView;
        }

    }

    static class ViewHolder {
        ImageView imageView;
        TextView tvTitle;
        TextView tvTime;
    }

}
