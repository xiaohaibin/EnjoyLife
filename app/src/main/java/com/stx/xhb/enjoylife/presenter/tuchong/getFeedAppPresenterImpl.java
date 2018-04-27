package com.stx.xhb.enjoylife.presenter.tuchong;

import android.text.TextUtils;

import com.xhb.core.base.BasePresenter;
import com.stx.xhb.enjoylife.model.entity.TuchongImagResponse;
import com.stx.xhb.enjoylife.model.http.ApiManager;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author: xiaohaibin.
 * @time: 2018/4/24
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class getFeedAppPresenterImpl extends BasePresenter<getFeedAppContact.View> implements getFeedAppContact {

    @Override
    public void getFeedAppImage(int page, String type, String posId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", String.valueOf(page));
        map.put("type", type);
        if (!TextUtils.isEmpty(posId)) {
            map.put("post_id", posId);
        }
        ApiManager.ApiFactory.createTuChongApi().getFeedApp(map).enqueue(new Callback<TuchongImagResponse>() {
            @Override
            public void onResponse(Call<TuchongImagResponse> call, Response<TuchongImagResponse> response) {
                if (response.isSuccessful() && response.body() != null&&response.body().getFeedList()!=null&&!response.body().getFeedList().isEmpty()) {
                    getView().onResponse(response.body().getFeedList(), response.body().isMore());
                }
            }

            @Override
            public void onFailure(Call<TuchongImagResponse> call, Throwable t) {
                getView().onFailure(t.getMessage());
            }
        });
    }
}
