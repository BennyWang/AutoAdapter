package com.benny.library.autoadapter.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.benny.library.autoadapter.viewholder.AbstractViewHolder;
import com.benny.library.autoadapter.IViewCreator;

import java.util.List;

/**
 * Created by benny on 2/26/16.
 */

public class AutoListAdapter<T> extends BaseAdapter {
    private IViewCreator<T> viewCreator;
    private IAdapterItemAccessor<T> itemAccessor;

    public AutoListAdapter(IAdapterItemAccessor<T> itemAccessor, IViewCreator<T> viewCreator) {
        this.viewCreator = viewCreator;
        this.itemAccessor = itemAccessor;
        itemAccessor.setDataSetChangedNotifier(new IDataSetChangedNotifier() {
            @Override
            public void notifyDataSetChanged() {
                AutoListAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public AutoListAdapter(List<T> items, IViewCreator<T> viewCreator) {
        this(new SimpleAdapterItemAccessor<T>(items), viewCreator);
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
