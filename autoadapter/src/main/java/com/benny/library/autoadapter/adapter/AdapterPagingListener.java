package com.benny.library.autoadapter.adapter;

/**
 * Created by benny on 2/26/16.
 */

public interface AdapterPagingListener<T> {
    void onLoadPage(T previous, int position);
}
