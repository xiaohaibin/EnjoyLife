package com.xhb.core.base;

public interface Presenter<V> {
    void attachView(V mvpView);
    void detachView();
}
