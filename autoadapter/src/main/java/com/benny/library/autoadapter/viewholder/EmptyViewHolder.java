package com.benny.library.autoadapter.viewholder;

import android.view.View;

/**
 * Created by benny on 3/7/16.
 */

public class EmptyViewHolder<T> implements IViewHolder<T> {
    @Override
    public void bind(View view) {
    }

    @Override
    public void update(T data) {
    }
}
