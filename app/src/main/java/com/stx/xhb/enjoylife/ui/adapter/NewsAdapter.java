package com.stx.xhb.enjoylife.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.model.entity.ZhiHuNewsResponse;
import com.stx.xhb.enjoylife.ui.activity.NewsDetailsActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: xiaohaibin.
 * @time: 2018/5/9
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class NewsAdapter extends BaseQuickAdapter<ZhiHuNewsResponse.StoriesBean,BaseViewHolder> {

    private Context mContext;

    public NewsAdapter(int layoutResId, Context context) {
        super(layoutResId);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, final ZhiHuNewsResponse.StoriesBean news) {
        if (news.getImages()!=null&&!news.getImages().isEmpty()) {
            ImageView imageView = holder.getView(R.id.thumbnail_image);
            Glide.with(imageView.getContext()).load(news.getImages().get(0)).into(imageView);
        }
        holder.setText(R.id.daily_title,news.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsDetailsActivity.start(mContext,String.valueOf(news.getId()),news.getTitle());
            }
        });
    }
}
