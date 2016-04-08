package com.benny.library.autoadapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.benny.library.autoadapter.listener.DataChangeListener;
import com.benny.library.autoadapter.listener.DataSetChangedNotifier;
import com.benny.library.autoadapter.viewcreator.IViewCreator;

import java.util.List;

/**
 * Created by benny on 2/26/16.
 */

public class AutoListAdapter<T> extends BaseAdapter {
    private IViewCreator<T> viewCreator;
    protected IAdapterItemAccessor<T> itemAccessor;

    public AutoListAdapter(IAdapterItemAccessor<T> itemAccessor, IViewCreator<T> viewCreator) {
        this.viewCreator = viewCreator;
        this.itemAccessor = itemAccessor;
        itemAccessor.setDataSetChangedNotifier(new DataSetChangedNotifier() {
            @Override
            public void notifyDataSetChanged() {
                AutoListAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public AutoListAdapter(List<T> items, IViewCreator<T> viewCreator) {
        this(new SimpleAdapterItemAccessor<T>(items), viewCreator);
    }

    public void swap(AutoListAdapter<T> adapter) {
        itemAccessor = adapter.itemAccessor;
        notifyDataSetChanged();
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

        ((DataChangeListener<T>)convertView.getTag()).onDataChange(getItem(position));
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

    @Override
    public boolean isEmpty() {
        return itemAccessor.isEmpty();
    }
}
