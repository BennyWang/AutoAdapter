package com.benny.app.sample.network.service.caishuo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by benny on 9/5/15.
 */

public class Error {
    public int code = 0;

    @SerializedName("msg")
    public String message;
}
