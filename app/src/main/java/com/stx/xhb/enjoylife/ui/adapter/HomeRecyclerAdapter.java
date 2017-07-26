package com.stx.xhb.enjoylife.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.meikoz.core.adapter.RecyclerAdapter;
import com.meikoz.core.adapter.RecyclerViewHolder;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.model.entity.ImageEntity;
import com.stx.xhb.enjoylife.ui.activity.PhotoViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 干货福利适配器
 */
public class HomeRecyclerAdapter extends RecyclerAdapter<ImageEntity.ResultsBean> {

    private ArrayList<String> imageList;
    private Context mContext;

    public HomeRecyclerAdapter(Context context, int layoutId, List<ImageEntity.ResultsBean> datas) {
        super(context, layoutId, datas);
        this.mContext=context;
        initData(datas);
    }

    private void initData(List<ImageEntity.ResultsBean> datas) {
        imageList=new ArrayList<>();
        for (int i = 0; i <datas.size(); i++) {
             imageList.add(datas.get(i).getUrl());
        }
    }


    @Override
    public void convert(final RecyclerViewHolder holder, final ImageEntity.ResultsBean item) {
        Glide.with(mContext).load(item.getUrl()).into((ImageView) holder.getView(R.id.iv));
        holder.setOnClickListener(R.id.iv, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PhotoViewActivity.class);
                intent.putStringArrayListExtra("image",imageList);
                mContext.startActivity(intent);
            }
        });
    }
}
