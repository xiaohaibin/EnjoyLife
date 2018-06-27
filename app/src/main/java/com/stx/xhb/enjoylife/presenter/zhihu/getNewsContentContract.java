package com.stx.xhb.enjoylife.presenter.zhihu;

import com.stx.xhb.enjoylife.model.entity.ZhiHuNewsContentResponse;
import com.xhb.core.base.IBaseView;
import com.xhb.core.base.IModel;
import com.xhb.core.model.annotation.Implement;

/**
 * @author: xiaohaibin.
 * @time: 2018/5/9
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
@Implement(getNewsContentIPresenterImpl.class)
public interface getNewsContentContract {

    interface Model extends IModel {
        void getNewsContent(String id);
    }

    interface View extends IBaseView {

        void onResponse(ZhiHuNewsContentResponse response);

        void onFailed(String msg);

        void showLoading();

        void hideLoading();
    }
}
