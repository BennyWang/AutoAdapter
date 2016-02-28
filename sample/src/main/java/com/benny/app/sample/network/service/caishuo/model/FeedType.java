package com.benny.app.sample.network.service.caishuo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by benny on 2/28/16.
 */

public enum FeedType {
    @SerializedName("news")
    NEWS,

    @SerializedName("news_stock")
    STOCK_NEWS,

    @SerializedName("news_position")
    POSITION_NEWS,

    @SerializedName("topic")
    TOPIC,

    @SerializedName("article")
    ARTICLE,

    BANNER
}
