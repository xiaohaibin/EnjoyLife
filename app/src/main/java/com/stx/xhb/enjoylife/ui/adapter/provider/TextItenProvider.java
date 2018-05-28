package com.stx.xhb.enjoylife.ui.adapter.provider;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.stx.xhb.enjoylife.R;
import com.stx.xhb.enjoylife.model.entity.VideoResponse;
import com.stx.xhb.enjoylife.ui.adapter.MultipleItemQuickAdapter;

/**
 * @author: xiaohaibin.
 * @time: 2018/5/28
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class TextItenProvider extends BaseItemProvider<VideoResponse.IssueListEntity.ItemListEntity,BaseViewHolder> {
    @Override
    public int viewType() {
        return MultipleItemQuickAdapter.TEXT;
    }

    @Override
    public int layout() {
        return R.layout.list_home_text_item;
    }

    @Override
    public void convert(BaseViewHolder holder, VideoResponse.IssueListEntity.ItemListEntity itemListEntity, int i) {
        if (itemListEntity.getType().startsWith("text")) {
            holder.setGone(R.id.tv_home_text, true);
        } else {
            holder.setGone(R.id.tv_home_text, false);
        }
        holder.setText(R.id.tv_home_text, itemListEntity.getData().getText());
    }
}
