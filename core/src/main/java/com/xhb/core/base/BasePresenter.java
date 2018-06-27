package com.xhb.core.base;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasePresenter<T, V extends BaseView> implements Presenter<V> {

    private V mView;

    private Call<T> mResponseCall;

    @Override
    public void attachView(V mvpView) {
        this.mView = mvpView;
    }


    @Override
    public void detachView() {
        this.mView = null;
        if (mResponseCall != null) {
            mResponseCall.cancel();
        }
    }

    protected void request(Call<T> call, final LoadTaskCallback<T> callback) {
        this.mResponseCall = call;
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onTaskLoaded(response.body());
                }else {
                    callback.onDataNotAvailable(response.message());
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                callback.onDataNotAvailable(t.getMessage());
            }
        });
    }

    public boolean isViewBind() {
        return mView == null;
    }

    public V getView() {
        return mView;
    }

    public interface LoadTaskCallback<T> {
        void onTaskLoaded(T data);

        void onDataNotAvailable(String msg);
    }

}
