package com.benny.library.autoadapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.benny.library.autoadapter.listener.DataSetChangedNotifier;
import com.benny.library.autoadapter.viewcreator.IViewCreator;
import com.benny.library.autoadapter.viewholder.DataGetter;
import com.benny.library.autoadapter.viewholder.IViewHolder;

import java.util.List;

/**
 * Created by benny on 2/27/16.
 */

public class AutoPagerAdapter<T> extends PagerAdapter {
    private IViewCreator<T> viewCreator;
    private AdapterView.OnItemClickListener itemClickListener;
    private AdapterView.OnItemLongClickListener itemLongClickListener;
    private View emptyView;
    protected IAdapterItemAccessor<T> itemAccessor;

    public AutoPagerAdapter(IAdapterItemAccessor<T> itemAccessor, IViewCreator<T> viewCreator) {
        this.viewCreator = viewCreator;
        this.itemAccessor = itemAccessor;
        itemAccessor.setDataSetChangedNotifier(new DataSetChangedNotifier() {
            @Override
            public void notifyDataSetChanged() {
                AutoPagerAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public AutoPagerAdapter(List<T> items, IViewCreator<T> viewCreator) {
        this(new SimpleAdapterItemAccessor<T>(items), viewCreator);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    @Override
    public int getCount() {
        int count = itemAccessor.size();
        if(emptyView != null) emptyView.setVisibility(count == 0 ? View.VISIBLE : View.GONE);
        return count;
    }

    public void setEmptyView(View view) {
        emptyView = view;
    }

    private T getItem(int position) {
        return (position < 0 || position >= getCount()) ? null : itemAccessor.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        viewCreator.viewTypeFor(getItem(position), position, getCount());
        final View itemView = viewCreator.view(container);
        container.addView(itemView);
        if(itemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(null, itemView, position, 0);
                }
            });
        }

        if(itemLongClickListener != null) {
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemLongClickListener.onItemLongClick(null, itemView, position, 0);
                    return false;
                }
            });
        }

        ((IViewHolder<T>)itemView.getTag()).onDataChange(new DataGetter<T>(getItem(position - 1), getItem(position), getItem(position + 1)), position);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(itemAccessor instanceof IPagerAdapterItemAccessor) {
            return ((IPagerAdapterItemAccessor) itemAccessor).getTitle(position);
        }
        return super.getPageTitle(position);
    }
}
