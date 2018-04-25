package com.stx.xhb.enjoylife.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xhb.core.adapter.RecyclerAdapter;
import com.xhb.core.adapter.RecyclerViewHolder;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.model.entity.TuchongImagEntity;
import com.stx.xhb.enjoylife.ui.activity.PhotoViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 干货福利适配器
 */
public class ImageRecyclerAdapter extends RecyclerAdapter<TuchongImagEntity.FeedListBean> {

    private Context mContext;

    public ImageRecyclerAdapter(Context context, int layoutId,List<TuchongImagEntity.FeedListBean> dataList) {
        super(context, layoutId, dataList);
        this.mContext = context;
    }

    @Override
    public void convert(final RecyclerViewHolder holder, final TuchongImagEntity.FeedListBean item) {
        List<TuchongImagEntity.FeedListBean.ImagesBean> images = item.getImages();
        if (images==null||images.isEmpty()){
            return;
        }
        TuchongImagEntity.FeedListBean.ImagesBean imagesBean = images.get(0);
        if (imagesBean!=null) {
            String url = "https://photo.tuchong.com/" + imagesBean.getUser_id() + "/f/" + imagesBean.getImg_id() + ".jpg";
            Glide.with(mContext).load(url).into((ImageView) holder.getView(R.id.iv));
        }
        final ArrayList<String> imageList=new ArrayList<>();
        for (int i = 0; i <images.size(); i++) {
            TuchongImagEntity.FeedListBean.ImagesBean imageData = images.get(i);
            String url = "https://photo.tuchong.com/" + imageData.getUser_id() + "/f/" + imageData.getImg_id() + ".jpg";
            imageList.add(url);
        }
        holder.setOnClickListener(R.id.iv, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PhotoViewActivity.class);
                intent.putStringArrayListExtra("image", imageList);
                intent.putExtra("pos",0);
                mContext.startActivity(intent);
            }
        });
    }

}
