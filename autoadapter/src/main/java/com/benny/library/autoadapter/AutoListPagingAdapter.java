package com.benny.library.autoadapter;

import android.view.View;
import android.view.ViewGroup;

import com.benny.library.autoadapter.listener.AdapterPagingCompleteListener;
import com.benny.library.autoadapter.listener.AdapterPagingListener;
import com.benny.library.autoadapter.viewcreator.IViewCreator;

import java.util.List;

/**
 * Created by benny on 2/26/16.
 */

public class AutoListPagingAdapter<T> extends AutoListAdapter<T> implements AdapterPagingCompleteListener {
    private boolean hasNextPage = true;
    private boolean loading = false;

    private AdapterPagingListener<T> pagingListener;

    public AutoListPagingAdapter(IAdapterItemAccessor<T> itemAccessor, IViewCreator<T> viewCreator) {
        super(itemAccessor, viewCreator);
    }

    public AutoListPagingAdapter(List<T> items, IViewCreator<T> viewCreator) {
        super(items, viewCreator);
    }

    public AutoListPagingAdapter(IAdapterItemAccessor<T> itemAccessor, IViewCreator<T> viewCreator, AdapterPagingListener<T> pagingListener) {
        super(itemAccessor, viewCreator);
        this.pagingListener = pagingListener;
    }

    public AutoListPagingAdapter(List<T> items, IViewCreator<T> viewCreator, AdapterPagingListener<T> pagingListener) {
        super(items, viewCreator);
        this.pagingListener = pagingListener;
    }

    public void setPagingListener(AdapterPagingListener<T> pagingListener) {
        this.pagingListener = pagingListener;
    }

    @Override
    public T getItem(int position) {
        return position < itemAccessor.size() ? super.getItem(position) : null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);

        if (position == getCount() - 1 && hasNextPage && !loading) {
            loading = true;
            if(pagingListener != null) pagingListener.onLoadPage(this, getItem(position - 1), position);
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return super.getCount() + (hasNextPage ? 1 : 0);
    }

    @Override
    public boolean isEmpty() {
        return itemAccessor.isEmpty() && !hasNextPage;
    }

    public void onPagingComplete(boolean hasNextPage) {
        loading = false;
        this.hasNextPage = hasNextPage;
        notifyDataSetChanged();
    }
}
