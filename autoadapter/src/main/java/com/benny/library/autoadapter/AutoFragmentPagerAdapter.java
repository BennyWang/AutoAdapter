package com.benny.library.autoadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.benny.library.autoadapter.listener.DataSetChangedListener;
import com.benny.library.autoadapter.viewcreator.IViewCreator;

import java.util.List;

/**
 * Created by benny on 2/27/16.
 */
public class AutoFragmentPagerAdapter extends FragmentPagerAdapter {
    protected IAdapterItemAccessor<Fragment> itemAccessor;

    public AutoFragmentPagerAdapter(FragmentManager fragmentManager, IAdapterItemAccessor<Fragment> itemAccessor) {
        super(fragmentManager);
        this.itemAccessor = itemAccessor;
        itemAccessor.setDataSetChangedListener(new DataSetChangedListener() {
            @Override
            public void onDataSetChanged() {
                AutoFragmentPagerAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getCount() {
        return itemAccessor.size();
    }

    @Override
    public Fragment getItem(int position) {
        return itemAccessor.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(itemAccessor instanceof IPagerAdapterItemAccessor) {
            return ((IPagerAdapterItemAccessor) itemAccessor).getTitle(position);
        }
        return super.getPageTitle(position);
    }
}
