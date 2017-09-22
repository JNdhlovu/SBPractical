package com.superbalist.sbpractical.ui;

import android.widget.AbsListView;

/**
 * Created by japhetndhlovu on 2017/09/22.
 */

public abstract class ContinousScrollListener implements AbsListView.OnScrollListener {

    /**
     * items we need below current scroll position to trigger loading
     */
    private int mVisibleThreshold = 10;

    /**
     * previous items total so we know if we did fetch items from remote
     */
    private int mPreviousTotalItemsCount = 0;

    /**
     * to know weather we are in loading state or not
     */
    private boolean mLoading = true;

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mLoading) {
            if (totalItemCount > mPreviousTotalItemsCount) {
                mLoading = false;
                mPreviousTotalItemsCount = totalItemCount;
            }
        }
        if (!mLoading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + mVisibleThreshold)) {
            mLoading = onLoadMore();
        }
    }

    /**
     *
     * @return true if loading
     * call back to get more data
     */
    public abstract boolean onLoadMore();
}
