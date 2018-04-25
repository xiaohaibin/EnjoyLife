package com.stx.xhb.enjoylife.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.xhb.core.adapter.RecyclerAdapter;
import com.xhb.core.adapter.RecyclerViewHolder;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.model.entity.SwitchVideoModel;
import com.stx.xhb.enjoylife.model.entity.TvModel;
import com.stx.xhb.enjoylife.ui.activity.VideoPlayActivity;
import com.stx.xhb.enjoylife.utils.NetConnectedUtils;

import java.util.List;

/**
 * Author：xiaohaibin
 * Time：2017/7/20
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：电视直播列表适配器
 */
public class TvShowRecyclerAdapter extends RecyclerAdapter<TvModel.DataBean> {
    private Context mContext;

    public TvShowRecyclerAdapter(Context context, int layoutId, List<TvModel.DataBean> datas) {
        super(context, layoutId, datas);
        this.mContext = context;
    }

    @Override
    public void convert(RecyclerViewHolder hepler, TvModel.DataBean dataBean) {
        hepler.setText(R.id.tv_name, dataBean.getName());
        RecyclerView rvSub = hepler.getView(R.id.rv_subList);
        rvSub.setLayoutManager(new GridLayoutManager(mContext, 4));
        rvSub.setAdapter(new RecyclerAdapter<SwitchVideoModel>(mContext, R.layout.list_item_selet_tv_sub, dataBean.getTv_list()) {
            @Override
            public void convert(RecyclerViewHolder hepler, final SwitchVideoModel switchVideoModel) {
                     hepler.setText(R.id.tv_name,switchVideoModel.getName());
                     hepler.itemView.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             if (NetConnectedUtils.isNetConnected(mContext)) {
                                 Intent intent = new Intent(mContext, VideoPlayActivity.class);
                                 Bundle bundle = new Bundle();
                                 bundle.putString(VideoPlayActivity.VIDEO_URL,switchVideoModel.getUrl());
                                 bundle.putString(VideoPlayActivity.VIDEO_TITLE, switchVideoModel.getName());
                                 bundle.putBoolean(VideoPlayActivity.TRANSITION, true);
                                 intent.putExtra("video", bundle);
                                 mContext.startActivity(intent);
                             } else {
                                 Toast.makeText(mContext, "网络异常，请稍后再试", Toast.LENGTH_SHORT).show();
                             }
                         }
                     });
            }
        });
    }
}
