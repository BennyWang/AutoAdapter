package com.benny.library.autoadapter;

import android.support.v7.widget.RecyclerView;

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

    public AutoRecyclerPagingAdapter(IAdapterItemAccessor<T> itemAccessor, IViewCreator<T> viewCreator, AdapterPagingListener<T> pagingListener) {
        super(itemAccessor, viewCreator);
        this.pagingListener = pagingListener;
    }

    public AutoRecyclerPagingAdapter(List<T> items, IViewCreator<T> viewCreator, AdapterPagingListener<T> pagingListener) {
        super(items, viewCreator);
        this.pagingListener = pagingListener;
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

    public void onPagingComplete(boolean hasNextPage) {
        loading = false;
        this.hasNextPage = hasNextPage;
        notifyDataSetChanged();
    }
}
