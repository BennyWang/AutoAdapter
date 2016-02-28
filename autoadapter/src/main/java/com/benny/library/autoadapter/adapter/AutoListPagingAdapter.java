package com.benny.library.autoadapter.adapter;

import android.view.View;
import android.view.ViewGroup;
import com.benny.library.autoadapter.IViewCreator;

import java.util.List;

/**
 * Created by benny on 2/26/16.
 */

public class AutoListPagingAdapter<T> extends AutoListAdapter<T> implements AdapterPagingCompleteHandler {
    private boolean hasNextPage = true;
    private boolean loading = false;

    private AdapterPagingListener<T> listener;

    public AutoListPagingAdapter(IAdapterItemAccessor<T> itemAccessor, IViewCreator<T> viewCreator) {
        super(itemAccessor, viewCreator);
    }

    public AutoListPagingAdapter(List<T> items, IViewCreator<T> viewCreator) {
        super(items, viewCreator);
    }

    public void setPagingListener(AdapterPagingListener<T> listener) {
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);

        if (position == getCount() - 1 && hasNextPage && !loading) {
            loading = true;
            listener.onLoadPage(this, getItem(position - 1), position);
        }

        return convertView;
    }

    public void loadComplete(boolean hasNextPage) {
        loading = false;
        this.hasNextPage = hasNextPage;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return super.getCount() + (hasNextPage ? 1 : 0);
    }
}
