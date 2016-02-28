package com.benny.library.autoadapter.adapter;

/**
 * Created by benny on 2/26/16.
 */

public interface IAdapterItemAccessor<T> {
    void setDataSetChangedNotifier(IDataSetChangedNotifier notifier);
    int size();
    T get(int position);
    boolean isEmpty();
}
