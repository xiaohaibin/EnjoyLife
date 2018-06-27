package com.xhb.core.base;

public interface IPresenter<V> {
    void attachView(V mvpView);
    void detachView();
}
