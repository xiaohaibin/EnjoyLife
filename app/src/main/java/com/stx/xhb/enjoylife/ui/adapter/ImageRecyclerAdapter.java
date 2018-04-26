package com.stx.xhb.enjoylife.ui.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xhb.core.adapter.RecyclerAdapter;
import com.xhb.core.adapter.RecyclerViewHolder;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.model.entity.TuchongImagEntity;
import com.stx.xhb.enjoylife.ui.activity.PhotoViewActivity;
import com.xhb.core.imageloader.ImageLoaderProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * 图虫摄影适配器
 */
public class ImageRecyclerAdapter extends RecyclerAdapter<TuchongImagEntity.FeedListBean> {

    public ImageRecyclerAdapter(Context context, int layoutId,List<TuchongImagEntity.FeedListBean> dataList) {
        super(context, layoutId, dataList);
    }

    @Override
    public void convert(final RecyclerViewHolder holder, final TuchongImagEntity.FeedListBean item) {
        final ImageView imageView = (ImageView) holder.getView(R.id.iv);
        List<TuchongImagEntity.FeedListBean.ImagesBean> images = item.getImages();
        if (images==null||images.isEmpty()){
            return;
        }
        TuchongImagEntity.FeedListBean.ImagesBean imagesBean = images.get(0);
        if (imagesBean!=null) {
            String url = "https://photo.tuchong.com/" + imagesBean.getUser_id() + "/f/" + imagesBean.getImg_id() + ".jpg";
            ImageLoaderProxy.getInstance().loadIntoUseFitWidth(imageView,url);
            ViewCompat.setTransitionName(imageView,url);
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
                if (mOnImageItemClickListener!=null){
                    mOnImageItemClickListener.setOnImageClick(imageView,imageList);
                }
            }
        });
        holder.itemView.setTag(item.getTitle());
    }

    private setOnImageItemClickListener mOnImageItemClickListener;

    public void setOnImageItemClickListener(setOnImageItemClickListener onImageItemClickListener) {
        mOnImageItemClickListener = onImageItemClickListener;
    }

    public interface setOnImageItemClickListener{
        void setOnImageClick(View view,ArrayList<String> imageList);
    }

}
