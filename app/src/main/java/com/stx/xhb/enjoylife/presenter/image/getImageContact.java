package com.stx.xhb.enjoylife.presenter.image;

import com.meikoz.core.base.BaseView;
import com.meikoz.core.model.annotation.Implement;
import com.stx.xhb.enjoylife.model.entity.ImageEntity;

import java.util.List;

/**
 * Author：xiaohaibin
 * Time：2017/7/26
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */
@Implement(getImagePresenterImpl.class)
public interface getImageContact {
    void getImageInfo(int size, int page);

    interface getImageView extends BaseView{
        void onResponse(List<ImageEntity.ResultsBean> response);

        void onFailure(String msg);
    }
}
