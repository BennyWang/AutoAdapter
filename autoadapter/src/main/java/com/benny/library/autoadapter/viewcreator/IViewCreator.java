package com.benny.library.autoadapter.viewcreator;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by benny on 2/15/16.
 */

public interface IViewCreator<T> {
    View view(ViewGroup container);
    int viewTypeFor(T data, int position, int itemCount);
    int viewTypeCount();
}
