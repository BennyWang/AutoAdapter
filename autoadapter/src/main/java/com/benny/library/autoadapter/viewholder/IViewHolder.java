package com.benny.library.autoadapter.viewholder;

import android.view.View;

import com.benny.library.autoadapter.listener.DataChangeListener;

/**
 * Created by benny on 2/15/16.
 */

public interface IViewHolder<T> extends DataChangeListener<T> {
    void bind(View view);
}
