package com.benny.library.autoadapter.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.benny.library.autoadapter.IViewCreator;

import java.util.List;

/**
 * Created by benny on 2/27/16.
 */
public class AutoViewPagerAdapter<T> extends PagerAdapter {
    private IViewCreator<T> viewCreator;
    protected IAdapterItemAccessor<T> itemAccessor;

    public AutoViewPagerAdapter(IAdapterItemAccessor<T> itemAccessor, IViewCreator<T> viewCreator) {
        this.viewCreator = viewCreator;
        this.itemAccessor = itemAccessor;
        itemAccessor.setDataSetChangedNotifier(new IDataSetChangedNotifier() {
            @Override
            public void notifyDataSetChanged() {
                AutoViewPagerAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public AutoViewPagerAdapter(List<T> items, IViewCreator<T> viewCreator) {
        this(new SimpleAdapterItemAccessor<T>(items), viewCreator);
    }

    @Override
    public int getCount() {
        return itemAccessor.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return viewCreator.view(container);
    }
}
