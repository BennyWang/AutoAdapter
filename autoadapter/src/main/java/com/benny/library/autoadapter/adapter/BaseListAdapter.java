package com.benny.library.autoadapter.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.benny.library.autoadapter.AbstractViewHolder;
import com.benny.library.autoadapter.IViewCreator;

/**
 * Created by benny on 2/26/16.
 */

public class BaseListAdapter<T> extends BaseAdapter {
    private IViewCreator<T> viewCreator;
    private IAdapterItemAccessor<T> itemAccessor;

    public BaseListAdapter(IViewCreator<T> viewCreator, IAdapterItemAccessor<T> itemAccessor) {
        this.viewCreator = viewCreator;
        this.itemAccessor = itemAccessor;
    }

    @Override
    public int getCount() {
        return itemAccessor.size();
    }

    @Override
    public T getItem(int position) {
        return itemAccessor.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = viewCreator.view(parent);
        }

        ((AbstractViewHolder<T>)convertView.getTag()).update(getItem(position));
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return viewCreator.viewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        return viewCreator.viewTypeFor(itemAccessor.get(position), position, getCount());
    }
}
