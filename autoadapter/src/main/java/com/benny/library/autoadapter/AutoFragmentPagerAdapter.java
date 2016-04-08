package com.benny.library.autoadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.benny.library.autoadapter.listener.DataSetChangedNotifier;

/**
 * Created by benny on 2/27/16.
 */
public class AutoFragmentPagerAdapter extends FragmentPagerAdapter {
    protected IAdapterItemAccessor<Fragment> itemAccessor;

    public AutoFragmentPagerAdapter(FragmentManager fragmentManager, IAdapterItemAccessor<Fragment> itemAccessor) {
        super(fragmentManager);
        this.itemAccessor = itemAccessor;
        itemAccessor.setDataSetChangedNotifier(new DataSetChangedNotifier() {
            @Override
            public void notifyDataSetChanged() {
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
