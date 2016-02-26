package com.benny.library.autoadapter.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.benny.library.autoadapter.IViewCreator;

/**
 * Created by benny on 2/27/16.
 */
public class BaseViewPagerAdapter<T> extends PagerAdapter {
    private IViewCreator<T> viewCreator;
    protected IAdapterItemAccessor<T> itemAccessor;

    public BaseViewPagerAdapter(IViewCreator<T> viewCreator, IAdapterItemAccessor<T> itemAccessor) {
        this.viewCreator = viewCreator;
        this.itemAccessor = itemAccessor;
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
