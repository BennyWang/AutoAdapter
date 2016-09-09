package com.benny.library.autoadapter;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.benny.library.autoadapter.listener.AdapterPagingCompleteListener;
import com.benny.library.autoadapter.listener.AdapterPagingListener;
import com.benny.library.autoadapter.viewcreator.IViewCreator;

import java.util.List;

/**
 * Created by benny on 2/26/16.
 */

public class AutoRecyclerPagingAdapter<T> extends AutoRecyclerAdapter<T> implements AdapterPagingCompleteListener {
    private AdapterPagingListener<T> pagingListener;
    private boolean hasNextPage = true;
    private boolean loading = false;

    private Handler handler = new Handler();

    public AutoRecyclerPagingAdapter(IAdapterItemAccessor<T> itemAccessor, IViewCreator<T> viewCreator) {
        super(itemAccessor, viewCreator);
    }

    public AutoRecyclerPagingAdapter(List<T> items, IViewCreator<T> viewCreator) {
        super(items, viewCreator);
    }

    public AutoRecyclerPagingAdapter(IAdapterItemAccessor<T> itemAccessor, IViewCreator<T> viewCreator, AdapterPagingListener<T> pagingListener) {
        super(itemAccessor, viewCreator);
        this.pagingListener = pagingListener;
    }

    public AutoRecyclerPagingAdapter(List<T> items, IViewCreator<T> viewCreator, AdapterPagingListener<T> pagingListener) {
        super(items, viewCreator);
        this.pagingListener = pagingListener;
    }

    public void setPagingListener(AdapterPagingListener<T> pagingListener) {
        this.pagingListener = pagingListener;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        final int fixPosition = position;

        if (position == getItemCount() - 1 && hasNextPage && !loading) {
            loading = true;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    pagingListener.onLoadPage(AutoRecyclerPagingAdapter.this, getItem(fixPosition - 1), fixPosition);
                }
            });
        }
    }

    @Override
    public T getItem(int position) {
        return position < itemAccessor.size() && position >= 0 ? super.getItem(position) : null;
    }

    @Override
    public int getItemCount() {
        int itemCount = super.getItemCount() + (hasNextPage && pagingListener != null ? 1 : 0);
        if(emptyView != null) emptyView.setVisibility(itemCount == 0 ? View.VISIBLE : View.GONE);
        return itemCount;
    }

    public void onPagingComplete(boolean hasNextPage) {
        loading = false;
        this.hasNextPage = hasNextPage;
        notifyDataSetChanged();
    }
}
