package com.stx.xhb.enjoylife.presenter;

import com.android.core.model.annotation.Implement;
import com.stx.xhb.enjoylife.presenter.impl.getVideoPresenterImpl;

/**
 * Created by Mr.xiao on 16/7/14.
 */
@Implement(getVideoPresenterImpl.class)
public interface getVideoPresenter {
    void getVideoInfo(boolean isMore, int num);
}
