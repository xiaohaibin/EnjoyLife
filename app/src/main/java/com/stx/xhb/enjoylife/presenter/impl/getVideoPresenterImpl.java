package com.stx.xhb.enjoylife.presenter.impl;

import com.stx.xhb.enjoylife.model.entity.VideoEntity;
import com.stx.xhb.enjoylife.model.http.ApiManager;
import com.stx.xhb.enjoylife.presenter.core.LoadListDataLogicImpl;
import com.stx.xhb.enjoylife.presenter.getVideoPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mr.xiao on 16/7/14.
 */
public class getVideoPresenterImpl extends LoadListDataLogicImpl implements getVideoPresenter {

    @Override
    public void getVideoInfo(final boolean isMore, int num) {
        ApiManager.ApiFactory.createVideoApi().getVideoEntity(num).enqueue(new Callback<VideoEntity>() {
            @Override
            public void onResponse(Call<VideoEntity> call, Response<VideoEntity> response) {
                   onLoadListSuccessHandle(response,isMore);
            }

            @Override
            public void onFailure(Call<VideoEntity> call, Throwable t) {
                   onLoadFail(t.getMessage());
            }
        });
    }
}
