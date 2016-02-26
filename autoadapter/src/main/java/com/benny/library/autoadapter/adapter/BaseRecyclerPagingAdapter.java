package com.benny.library.autoadapter.adapter;

import android.support.v7.widget.RecyclerView;
import com.benny.library.autoadapter.IViewCreator;

/**
 * Created by benny on 2/26/16.
 */

public class BaseRecyclerPagingAdapter<T> extends BaseRecyclerAdapter<T> {
    private AdapterPagingListener<T> pagingListener;
    private boolean hasNextPage = true;
    private boolean loading = false;

    public BaseRecyclerPagingAdapter(IViewCreator<T> viewCreator, IAdapterItemAccessor<T> itemAccessor) {
        super(viewCreator, itemAccessor);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);

        if (position == getItemCount() - 1 && hasNextPage && !loading) {
            loading = true;
            pagingListener.onLoadPage(itemAccessor.get(position - 1), position);
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
