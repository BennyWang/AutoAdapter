package com.benny.library.autoadapter.adapter;

import android.support.v7.widget.RecyclerView;
import com.benny.library.autoadapter.IViewCreator;

import java.util.List;

/**
 * Created by benny on 2/26/16.
 */

public class AutoRecyclerPagingAdapter<T> extends AutoRecyclerAdapter<T> implements AdapterPagingCompleteHandler {
    private AdapterPagingListener<T> pagingListener;
    private boolean hasNextPage = true;
    private boolean loading = false;

    public AutoRecyclerPagingAdapter(IAdapterItemAccessor<T> itemAccessor, IViewCreator<T> viewCreator) {
        super(itemAccessor, viewCreator);
    }

    public AutoRecyclerPagingAdapter(T[] items, IViewCreator<T> viewCreator) {
        super(items, viewCreator);
    }

    public AutoRecyclerPagingAdapter(List<T> items, IViewCreator<T> viewCreator) {
        super(items, viewCreator);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);

        if (position == getItemCount() - 1 && hasNextPage && !loading) {
            loading = true;
            pagingListener.onLoadPage(this, itemAccessor.get(position - 1), position);
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + (hasNextPage ? 1 : 0);
    }

    public void loadComplete(boolean hasNextPage) {
        loading = false;
        this.hasNextPage = hasNextPage;
        notifyDataSetChanged();
    }

    public void setPagingListener(AdapterPagingListener<T> pagingListener) {
        this.pagingListener = pagingListener;
    }
}
