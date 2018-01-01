package com.medidata.model;

/**
 * Created by sakibchoudhury on 02/12/17.
 */
public enum Title {
    MR("Mr"), MRS("Mrs"), MS("Ms");

    private String titleValue;

    Title(String titleValue) {
        this.titleValue = titleValue;
    }

    public String getTitleValue() {
        return titleValue;
    }
}
