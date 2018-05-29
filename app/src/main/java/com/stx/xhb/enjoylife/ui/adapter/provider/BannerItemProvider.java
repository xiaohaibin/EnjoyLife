package com.stx.xhb.enjoylife.ui.adapter.provider;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.model.entity.VideoResponse;
import com.stx.xhb.enjoylife.ui.adapter.VideoRecyclerAdapter;

/**
 * @author: xiaohaibin.
 * @time: 2018/5/29
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class BannerItemProvider extends BaseItemProvider<VideoResponse.IssueListEntity.ItemListEntity,BaseViewHolder> {
    @Override
    public int viewType() {
        return VideoRecyclerAdapter.BANNER;
    }

    @Override
    public int layout() {
        return R.layout.list_home_item_banner;
    }

    @Override
    public void convert(BaseViewHolder holder, VideoResponse.IssueListEntity.ItemListEntity itemListEntity, int i) {
        ImageView imageView = holder.getView(R.id.iv_banner);
        Glide.with(imageView.getContext())
                .load(itemListEntity.getData().getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}
