package com.benny.library.autoadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.benny.library.autoadapter.factory.ViewHolderFactory;

/**
 * Created by benny on 2/15/16.
 */

public class ViewCreator<T> implements IViewCreator<T> {
    private int resId;
    private Class<?> vhClass;

    public ViewCreator(int resId, Class<?> vhClass) {
        this.resId = resId;
        this.vhClass = vhClass;
    }

    @Override
    public View view(ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(resId, container, false);
        view.setTag(ViewHolderFactory.create(view, vhClass));
        return view;
    }

    @Override
    public int viewTypeFor(T data, int position, int itemCount) {
        return 0;
    }

    @Override
    public int viewTypeCount() {
        return 1;
    }
}
