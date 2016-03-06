package com.benny.library.autoadapter.listener;

/**
 * Created by benny on 2/26/16.
 */

public interface AdapterPagingListener<T> {
    void onLoadPage(AdapterPagingCompleteListener receiver, T previous, int position);
}
