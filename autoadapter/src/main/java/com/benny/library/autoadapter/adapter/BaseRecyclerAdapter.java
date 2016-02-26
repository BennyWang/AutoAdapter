package com.benny.library.autoadapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.benny.library.autoadapter.AbstractViewHolder;
import com.benny.library.autoadapter.IViewCreator;

/**
 * Created by benny on 2/26/16.
 */

public class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private AdapterView.OnItemClickListener itemClickListener;
    private IViewCreator<T> viewCreator;
    protected IAdapterItemAccessor<T> itemAccessor;

    public BaseRecyclerAdapter(IViewCreator<T> viewCreator, IAdapterItemAccessor<T> itemAccessor) {
        this.viewCreator = viewCreator;
        this.itemAccessor = itemAccessor;
    }

    protected RecyclerView.ViewHolder createViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return createViewHolder(viewCreator.view(viewGroup));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((ViewHolder)viewHolder).notifyPropertyChange(itemAccessor.get(position), position);
    }

    @Override
    public int getItemCount() {
        return itemAccessor.size();
    }

    @Override
    public int getItemViewType(int position) {
        return viewCreator.viewTypeFor(itemAccessor.get(position), position, getItemCount());
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(final View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(null, itemView, getLayoutPosition(), 0);
                }
            });
        }

        @SuppressWarnings("unchecked")
        void notifyPropertyChange(T data, int position) {
            ((AbstractViewHolder<T>)itemView.getTag()).update(data);
        }
    }
}
