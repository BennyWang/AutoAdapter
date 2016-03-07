package com.benny.library.autoadapter;

import com.benny.library.autoadapter.listener.DataSetChangedListener;

/**
 * Created by benny on 2/26/16.
 */

public interface IAdapterItemAccessor<T> {
    void setDataSetChangedListener(DataSetChangedListener listener);
    int size();
    T get(int position);
    boolean isEmpty();
}
