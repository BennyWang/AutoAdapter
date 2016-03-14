package com.benny.library.autoadapter;

/**
 * Created by benny on 2/26/16.
 */

public interface IPagerAdapterItemAccessor<T> extends IAdapterItemAccessor<T> {
    String getTitle(int position);
}
