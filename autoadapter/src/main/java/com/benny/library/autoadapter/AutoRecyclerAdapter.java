package com.benny.library.autoadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.benny.library.autoadapter.listener.DataChangeListener;
import com.benny.library.autoadapter.listener.DataSetChangedNotifier;
import com.benny.library.autoadapter.viewcreator.IViewCreator;

import java.util.List;

/**
 * Created by benny on 2/26/16.
 */

public class AutoRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private AdapterView.OnItemClickListener itemClickListener;
    private IViewCreator<T> viewCreator;
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
        ((ViewHolder)viewHolder).notifyDataChange(getItem(position), position);
    }

    public T getItem(int position) {
        return itemAccessor.get(position);
    }

    @Override
    public int getItemCount() {
        return itemAccessor.size();
    }

    @Override
    public int getItemViewType(int position) {
        return viewCreator.viewTypeFor(getItem(position), position, getItemCount());
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
        void notifyDataChange(T data, int position) {
            ((DataChangeListener<T>)itemView.getTag()).onDataChange(data);
        }
    }
}
