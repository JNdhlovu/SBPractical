package com.superbalist.sbpractical.entities;

/**
 * Created by japhetndhlovu on 2017/09/21.
 */

public class Filter {
    private boolean category;
    private boolean exclude;

    public boolean isCategory() {
        return category;
    }

    public void setCategory(boolean category) {
        this.category = category;
    }

    public boolean isExclude() {
        return exclude;
    }

    public void setExclude(boolean exclude) {
        this.exclude = exclude;
    }
}
