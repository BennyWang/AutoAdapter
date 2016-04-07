package com.benny.library.autoadapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.benny.library.autoadapter.listener.DataChangeListener;
import com.benny.library.autoadapter.listener.DataSetChangedListener;
import com.benny.library.autoadapter.viewcreator.IViewCreator;

import java.util.List;

/**
 * Created by benny on 2/27/16.
 */

public class AutoPagerAdapter<T> extends PagerAdapter {
    private IViewCreator<T> viewCreator;
    protected IAdapterItemAccessor<T> itemAccessor;
    private AdapterView.OnItemClickListener itemClickListener;

    public AutoPagerAdapter(IAdapterItemAccessor<T> itemAccessor, IViewCreator<T> viewCreator) {
        this.viewCreator = viewCreator;
        this.itemAccessor = itemAccessor;
        itemAccessor.setDataSetChangedListener(new DataSetChangedListener() {
            @Override
            public void onDataSetChanged() {
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

    @Override
    public int getCount() {
        return itemAccessor.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final View itemView = viewCreator.view(container);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener != null) itemClickListener.onItemClick(null, itemView, position, 0);
            }
        });
        ((DataChangeListener<T>)itemView.getTag()).onDataChange(itemAccessor.get(position));
        return itemView;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(itemAccessor instanceof IPagerAdapterItemAccessor) {
            return ((IPagerAdapterItemAccessor) itemAccessor).getTitle(position);
        }
        return super.getPageTitle(position);
    }
}
