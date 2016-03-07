package com.benny.app.sample.network.service.caishuo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by benny on 2/28/16.
 */

public enum FeedItemType {

    @SerializedName("stock")
    STOCK,

    @SerializedName("basket")
    BASKET,

    @SerializedName("news")
    NEWS,

    @SerializedName("topic")
    TOPIC,

    @SerializedName("plate")
    PLATE
}
