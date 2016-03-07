package com.benny.app.sample.network.service.caishuo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by benny on 2/28/16.
 */
public enum ListedState {

    @SerializedName("0")
    ABNORMAL("停牌"),

    @SerializedName("1")
    NORMAL("正常"),

    @SerializedName("2")
    DELISTED("退市"),

    @SerializedName("3")
    NOTLISTED("未上市"),

    UNKNOWN("未知");

    private String desc;

    ListedState(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return desc;
    }
}
