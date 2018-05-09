package com.stx.xhb.enjoylife.presenter.zhihu;

import com.stx.xhb.enjoylife.model.entity.ZhiHuNewsContentResponse;
import com.stx.xhb.enjoylife.model.http.ApiManager;
import com.stx.xhb.enjoylife.utils.ToastUtil;
import com.xhb.core.base.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author: xiaohaibin.
 * @time: 2018/5/9
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class getNewsContentPresenterImpl extends BasePresenter<getNewsContentContract.View> implements getNewsContentContract {

    private Call<ZhiHuNewsContentResponse> mZhiHuNewsContent;

    @Override
    public void getNewsContent(String id) {
        getView().showLoading();
        mZhiHuNewsContent = ApiManager.ApiFactory.creatZhiHuApi().getZhiHuNewsContent(id);
        mZhiHuNewsContent.enqueue(new Callback<ZhiHuNewsContentResponse>() {
            @Override
            public void onResponse(Call<ZhiHuNewsContentResponse> call, Response<ZhiHuNewsContentResponse> response) {
                getView().hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    getView().onResponse(response.body());
                } else {
                    ToastUtil.show(response.message());
                }
            }

            @Override
            public void onFailure(Call<ZhiHuNewsContentResponse> call, Throwable t) {
                ToastUtil.show(t.getMessage());
                getView().hideLoading();
            }
        });
    }

    public void cancleNetWork(){
        if (mZhiHuNewsContent!=null){
            mZhiHuNewsContent.cancel();
        }
    }
}
