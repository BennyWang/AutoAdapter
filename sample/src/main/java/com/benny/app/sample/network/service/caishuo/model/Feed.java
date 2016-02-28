package com.benny.app.sample.network.service.caishuo.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by benny on 2/28/16.
 */

public class Feed {

    @SerializedName("feed_type")
    public FeedType feedType;

    public FeedType type;

    @SerializedName("id")
    public String guid;

    public String title;

    public String link;

    public String url;

    @SerializedName("content")
    public String description;

    public String category;
    public String source;

    @SerializedName("pics")
    public String[] images;

    public Item[] items;

    @SerializedName("feeder")
    public User author;

    @SerializedName("created_at")
    public Date pubDate;

    public static class Item {
        public String id;
        public String name;
        public FeedItemType type;
    }

    public static class StockItem extends Item {
        public StockItem() {
            this.type = FeedItemType.STOCK;
        }

        public String symbol;

        @SerializedName("realtime_price")
        public float price;
        @SerializedName("change_percent")
        public float changePercent;
        @SerializedName("listed_state")
        public int listedState;

        public boolean followed;

        public boolean positioned;
    }

    public static class NewsItem extends Item {
        public NewsItem() {
            this.type = FeedItemType.NEWS;
        }

        public String source;
        public String category;
        public String[] tags;
        public String url;
    }

    public static class BasketItem extends Item {
        public BasketItem() {
            this.type = FeedItemType.BASKET;
        }

        @SerializedName("total_return")
        public float totalReturn;
    }

    public static class TopicItem extends NewsItem {
        public TopicItem() {
            this.type = FeedItemType.TOPIC;
        }
    }

    public static class PlateItem extends Item {
        public PlateItem() {
            type = FeedItemType.PLATE;
        }

    }
}
