package com.benny.library.autoadapter.viewholder;

import android.content.Context;
import android.view.View;

/**
 * Created by benny on 2/15/16.
 */

public interface AbstractViewHolder<T> {
    void bind(View view);
    void update(T data);
}
