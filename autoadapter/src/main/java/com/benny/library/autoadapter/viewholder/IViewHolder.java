package com.benny.library.autoadapter.viewholder;

import android.view.View;

/**
 * Created by benny on 2/15/16.
 */

public interface IViewHolder<T> {
    void bind(View view);
    void onDataChange(DataGetter<T> getter, int position);
}
