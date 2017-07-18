package com.stx.xhb.enjoylife.presenter;

import com.android.core.model.annotation.Implement;
import com.stx.xhb.enjoylife.presenter.impl.getImagePresenterImpl;

/**
 * Created by Mr.xiao on 16/7/7.
 */
@Implement(getImagePresenterImpl.class)
public interface getImagePresenter {
    void getImageInfo(boolean isMore, int page);
}
