package com.benny.library.autoadapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.benny.library.autoadapter.listener.DataSetChangedNotifier;
import com.benny.library.autoadapter.viewcreator.IViewCreator;
import com.benny.library.autoadapter.viewholder.DataGetter;
import com.benny.library.autoadapter.viewholder.IViewHolder;

import java.util.List;

/**
 * Created by benny on 2/26/16.
 */

public class AutoListAdapter<T> extends BaseAdapter {
    private AdapterView.OnItemClickListener itemClickListener;
    private AdapterView.OnItemLongClickListener itemLongClickListener;

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
        return (position < 0 || position >= getCount()) ? null : itemAccessor.get(position);
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

        if(itemClickListener != null) {
            final View finalView = convertView;
            final int finalPosition = position;
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(null, finalView, finalPosition, 0);
                }
            });
        }

        if(itemLongClickListener != null) {
            final View finalView = convertView;
            final int finalPosition = position;
            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemLongClickListener.onItemLongClick(null, finalView, finalPosition, 0);
                    return true;
                }
            });
        }

        ((IViewHolder<T>)convertView.getTag()).onDataChange(new DataGetter<T>(getItem(position - 1), getItem(position), getItem(position + 1)), position);
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return viewCreator.viewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        return viewCreator.viewTypeFor(getItem(position), position, getCount());
    }

    @Override
    public boolean isEmpty() {
        return itemAccessor.isEmpty();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }
}
