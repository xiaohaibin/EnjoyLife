package com.stx.xhb.enjoylife.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.CardViewHolder> {

    private List<ZhiHuNewsResponse.StoriesBean> datas;
    private Context mContext;

    public NewsAdapter(List<ZhiHuNewsResponse.StoriesBean> datas, Context context) {
        this.datas = datas;
        mContext = context;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news, parent,false));
    }

    @Override
    public void onBindViewHolder(final CardViewHolder holder, int position) {
        final ZhiHuNewsResponse.StoriesBean news = datas.get(position);
        if (news.getImages()!=null&&!news.getImages().isEmpty()) {
            Glide.with(holder.newsImage.getContext()).load(news.getImages().get(0)).into(holder.newsImage);
        }
        holder.questionTitle.setText(news.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsDetailsActivity.start(mContext,String.valueOf(news.getId()),news.getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.thumbnail_image)
        ImageView newsImage;
        @Bind(R.id.daily_title)
        TextView questionTitle;
        @Bind(R.id.card_share_overflow)
        ImageView overflow;

        public CardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
