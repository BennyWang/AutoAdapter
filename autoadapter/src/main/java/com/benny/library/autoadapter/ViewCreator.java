package com.benny.library.autoadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.benny.library.autoadapter.factory.Factory;
import com.benny.library.autoadapter.viewholder.AbstractViewHolder;

/**
 * Created by benny on 2/15/16.
 */

public class ViewCreator<T> implements IViewCreator<T> {
    private int resId;
    private Factory<? extends AbstractViewHolder<T>> factory;

    public ViewCreator(int resId, Factory<? extends AbstractViewHolder<T>> factory) {
        this.resId = resId;
        this.factory = factory;
    }

    @Override
    public View view(ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(resId, container, false);
        AbstractViewHolder<T> vh = factory.create();
        vh.bind(view);
        view.setTag(vh);
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
