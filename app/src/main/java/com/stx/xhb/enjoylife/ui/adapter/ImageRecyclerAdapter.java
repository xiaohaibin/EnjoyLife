package com.stx.xhb.enjoylife.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.meikoz.core.adapter.RecyclerAdapter;
import com.meikoz.core.adapter.RecyclerViewHolder;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.ui.activity.PhotoViewActivity;

import java.util.ArrayList;

/**
 * 干货福利适配器
 */
public class ImageRecyclerAdapter extends RecyclerAdapter<String> {

    private ArrayList<String> imageList;
    private Context mContext;

    public ImageRecyclerAdapter(Context context, int layoutId, ArrayList<String> datas) {
        super(context, layoutId, datas);
        this.mContext = context;
        this.imageList=datas;
    }

    @Override
    public void convert(final RecyclerViewHolder holder, final String item) {
        Glide.with(mContext).load(item).into((ImageView) holder.getView(R.id.iv));
        holder.setOnClickListener(R.id.iv, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PhotoViewActivity.class);
                intent.putStringArrayListExtra("image", imageList);
                intent.putExtra("pos", holder.getmPosition());
                mContext.startActivity(intent);
            }
        });
    }

}
