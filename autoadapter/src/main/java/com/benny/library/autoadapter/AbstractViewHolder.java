package com.benny.library.autoadapter;

import android.content.Context;
import android.view.View;

/**
 * Created by benny on 2/15/16.
 */

public abstract class AbstractViewHolder<T> {
    protected Context context;

    public AbstractViewHolder(View view) {
        context = view.getContext();
    }

    public abstract void update(T data);
}
