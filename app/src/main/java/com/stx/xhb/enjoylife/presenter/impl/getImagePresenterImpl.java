package com.stx.xhb.enjoylife.presenter.impl;

import com.stx.xhb.enjoylife.model.entity.ImageEntity;
import com.stx.xhb.enjoylife.model.http.ApiManager;
import com.stx.xhb.enjoylife.presenter.core.LoadListDataLogicImpl;
import com.stx.xhb.enjoylife.presenter.getImagePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mr.xiao on 16/7/7.
 */
public class getImagePresenterImpl extends LoadListDataLogicImpl implements getImagePresenter {

    @Override
    public void getImageInfo(final boolean isMore, int page) {
        ApiManager.ApiFactory.createApi().getImageEntity(page).enqueue(new Callback<ImageEntity>() {
            @Override
            public void onResponse(Call<ImageEntity> call, Response<ImageEntity> response) {
                   onLoadListSuccessHandle(response,isMore);
            }

            @Override
            public void onFailure(Call<ImageEntity> call, Throwable t) {
                    onLoadFail("服务器请求失败");
            }
        });
    }
}
