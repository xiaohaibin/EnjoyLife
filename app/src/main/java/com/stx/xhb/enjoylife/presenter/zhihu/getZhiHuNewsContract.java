package com.stx.xhb.enjoylife.presenter.zhihu;

import com.stx.xhb.enjoylife.model.entity.ZhiHuNewsResponse;
import com.xhb.core.base.BaseView;
import com.xhb.core.model.annotation.Implement;

/**
 * @author: xiaohaibin.
 * @time: 2018/5/9
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
@Implement(getZhiHuNewsPresenterImpl.class)
public interface getZhiHuNewsContract {

    void getNews(String url);

    void getNewsBefore(String time);

    interface View extends BaseView {

        void onResponse(ZhiHuNewsResponse zhiHuNewsResponse);

        void onFailure(String msg);
    }
}
