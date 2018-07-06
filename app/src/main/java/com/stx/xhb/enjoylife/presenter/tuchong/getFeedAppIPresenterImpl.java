package com.stx.xhb.enjoylife.presenter.tuchong;

import android.text.TextUtils;

import com.xhb.core.base.BaseIPresenter;
import com.stx.xhb.enjoylife.model.entity.TuchongImagResponse;
import com.stx.xhb.enjoylife.model.http.ApiManager;

import java.util.HashMap;

/**
 * @author: xiaohaibin.
 * @time: 2018/4/24
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class getFeedAppIPresenterImpl extends BaseIPresenter<TuchongImagResponse, getFeedAppContact.View> implements getFeedAppContact.Model {

    @Override
    public void getFeedAppImage(int page, String type, String posId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", String.valueOf(page));
        map.put("type", type);
        if (!TextUtils.isEmpty(posId)) {
            map.put("post_id", posId);
        }
        request(ApiManager.ApiFactory.createTuChongApi().getFeedApp(map), new LoadTaskCallback<TuchongImagResponse>() {
            @Override
            public void onTaskLoaded(TuchongImagResponse data) {
                if (getView() != null && data.getFeedList() != null && !data.getFeedList().isEmpty()) {
                    getView().onResponse(data.getFeedList(), data.isMore());
                }
            }

            @Override
            public void onDataNotAvailable(String msg) {
                if (getView() != null) {
                    getView().onFailure(msg);
                }
            }
        });
    }
}
