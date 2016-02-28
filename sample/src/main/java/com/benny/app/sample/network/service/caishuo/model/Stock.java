package com.benny.app.sample.network.service.caishuo.model;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class Stock {
    public String id;
    public String symbol;
    public String name;
    
    @SerializedName("com_name")
    public String cnName;
    
    @SerializedName("chi_spelling")
    public String chSpelling;

    public String screenshot;
    public boolean followed = false;
    public boolean positioned = false;

    @SerializedName("comments_count")
    public int commentsCount = 0;

    @SerializedName("trading_status")
    public String tradingStatus;

    @SerializedName("trading_time")
    public Date tradingTime;

    @SerializedName("realtime_price")
    public float realtimePrice = 0f;

    @SerializedName("change_percent")
    public float changePercent = 0f;

    @SerializedName("change_price")
    public float changePrice = 0f;

    public float volume = 0f;

    public float turnover = 0f;

    public MarketType market = MarketType.SH_SZ;

    @SerializedName("previous_close")
    public float previousClose = 0f;

    public float open = 0f;
    public float high = 0f;
    public float low = 0f;

    @SerializedName("high52_weeks")
    public float high52Weeks = 0f;

    @SerializedName("low52_weeks")
    public float low52Weeks = 0f;


    @SerializedName("turnover_rate")
    public float turnoverRate = 0f;

    @SerializedName("volume_ratio")
    public float volumeRatio = 0f;

    @SerializedName("bid_ratio")
    public float bidRatio = 0f;


    @SerializedName("exist_reminder")
    public boolean existReminder = false;

    @SerializedName("listed_state")
    public int listedState = 0;

    public static final int LISTED_STATE_ABNORMAL = 0;
    public static final int LISTED_STATE_NORMAL = 1;
    public static final int LISTED_STATE_DELISTED = 2;
    public static final int LISTED_STATE_NOTLISTED = 3;

    public static String  listedStateDescription(int listedState) {
        switch (listedState) {
            case Stock.LISTED_STATE_ABNORMAL:
                return "停牌";
            case Stock.LISTED_STATE_NORMAL:
                return "正常";
            case Stock.LISTED_STATE_DELISTED:
                return "退市";
            case Stock.LISTED_STATE_NOTLISTED:
                return "未上市";
            default:
                return "未知";
        }
    }
}
