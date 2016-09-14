package com.benny.library.autoadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.benny.library.autoadapter.listener.DataSetChangedNotifier;
import com.benny.library.autoadapter.viewcreator.IViewCreator;
import com.benny.library.autoadapter.viewholder.DataGetter;
import com.benny.library.autoadapter.viewholder.IViewHolder;

import java.util.List;

/**
 * Created by benny on 2/26/16.
 */

public class AutoRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private AdapterView.OnItemClickListener itemClickListener;
    private AdapterView.OnItemLongClickListener itemLongClickListener;

    private IViewCreator<T> viewCreator;
    protected View emptyView;
    protected IAdapterItemAccessor<T> itemAccessor;

    public AutoRecyclerAdapter(IAdapterItemAccessor<T> itemAccessor, IViewCreator<T> viewCreator) {
        this.viewCreator = viewCreator;
        this.itemAccessor = itemAccessor;
        itemAccessor.setDataSetChangedNotifier(new DataSetChangedNotifier() {
            @Override
            public void notifyDataSetChanged() {
                AutoRecyclerAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public AutoRecyclerAdapter(List<T> items, IViewCreator<T> viewCreator) {
        this(new SimpleAdapterItemAccessor<T>(items), viewCreator);
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
        ((ViewHolder)viewHolder).notifyDataChange(new DataGetter<T>(getItem(position - 1), getItem(position), getItem(position + 1)), position);
    }

    public T getItem(int position) {
        return (position < 0 || position >= getItemCount()) ? null : itemAccessor.get(position);
    }

    @Override
    public int getItemCount() {
        int itemCount = itemAccessor.size();
        if(emptyView != null) emptyView.setVisibility(itemCount == 0 ? View.VISIBLE : View.GONE);
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        return viewCreator.viewTypeFor(getItem(position), position, getItemCount());
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    public void setEmptyView(View view) {
        emptyView = view;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(final View itemView) {
            super(itemView);

            if(itemClickListener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClickListener.onItemClick(null, itemView, getLayoutPosition(), 0);
                    }
                });
            }

            if(itemLongClickListener != null) {
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        itemLongClickListener.onItemLongClick(null, itemView, getLayoutPosition(), 0);
                        return true;
                    }
                });
            }
        }

        @SuppressWarnings("unchecked")
        void notifyDataChange(DataGetter<T> getter, int position) {
            ((IViewHolder<T>)itemView.getTag()).onDataChange(getter, position);
        }
    }
}
