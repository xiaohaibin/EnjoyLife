package com.stx.xhb.enjoylife.presenter.image;

import com.meikoz.core.base.BasePresenter;
import com.stx.xhb.enjoylife.model.entity.ImageEntity;
import com.stx.xhb.enjoylife.model.http.ApiManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mr.xiao on 16/7/7.
 */
public class getImagePresenterImpl extends BasePresenter<getImageContact.getImageView> implements getImageContact {

    @Override
    public void getImageInfo(int size, int page) {
        ApiManager.ApiFactory.createApi().getImageEntity(size,page).enqueue(new Callback<ImageEntity>() {
            @Override
            public void onResponse(Call<ImageEntity> call, Response<ImageEntity> response) {
                if (response.isSuccessful()) {
                    getView().onResponse(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<ImageEntity> call, Throwable t) {
                getView().onFailure(t.getMessage());
            }
        });
    }
}
