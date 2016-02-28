package com.benny.app.sample.network.service.caishuo.model;

/**
 * Created by benny on 9/5/15.
 */

public class CaishuoEnveloped<T> {

    public int status = 0;
    public Error error;
    public T data;
}
