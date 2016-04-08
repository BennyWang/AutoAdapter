package com.benny.library.autoadapter;

import com.benny.library.autoadapter.listener.DataSetChangedNotifier;

/**
 * Created by benny on 2/26/16.
 */

public interface IAdapterItemAccessor<T> {
    void setDataSetChangedNotifier(DataSetChangedNotifier listener);
    int size();
    T get(int position);
    boolean isEmpty();
}
