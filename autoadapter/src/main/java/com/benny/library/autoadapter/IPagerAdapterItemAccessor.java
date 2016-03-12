package com.benny.library.autoadapter;

import com.benny.library.autoadapter.listener.DataSetChangedListener;

/**
 * Created by benny on 2/26/16.
 */

public interface IPagerAdapterItemAccessor<T> extends IAdapterItemAccessor<T> {
    String getTitle(int position);
}
