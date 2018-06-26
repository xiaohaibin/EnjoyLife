package com.xhb.core.base;

import retrofit2.Call;

public class BasePresenter<V extends BaseView> implements Presenter<V> {

    private V mView;

    private Call<?> mResponseCall;

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

    protected void addCall(Call<?> call) {
        this.mResponseCall = call;
    }

    public boolean isViewBind() {
        return mView == null;
    }

    public V getView() {
        return mView;
    }

}
