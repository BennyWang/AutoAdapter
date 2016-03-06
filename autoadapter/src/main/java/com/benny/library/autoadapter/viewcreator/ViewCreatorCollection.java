package com.benny.library.autoadapter.viewcreator;

import android.view.View;
import android.view.ViewGroup;
import com.benny.library.autoadapter.utils.Fun3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benny on 2/15/16.
 */

public class ViewCreatorCollection<T> implements IViewCreator<T> {
    private int viewTypeBegin = 0;
    private int lastViewType = -1;

    private List<ViewTypeFilter<T> > viewTypeFilters = new ArrayList<ViewTypeFilter<T>>();

    public ViewCreatorCollection<T> add(Fun3<T, Integer, Integer, Boolean> filter, ViewCreator<T> creator) {
        viewTypeFilters.add(new ViewTypeFilter<T>(filter, creator, viewTypeBegin++));
        return this;
    }

    private ViewTypeFilter<T> filter(T data, int position, int itemCount) {
        for(ViewTypeFilter<T> viewTypeFilter : viewTypeFilters) {
            if(viewTypeFilter.canProcess(data, position, itemCount)) return viewTypeFilter;
        }
        throw new RuntimeException("can not process view type for: " + data.toString());
    }

    @Override
    public View view(ViewGroup container) {
        int index = (lastViewType != -1) ? lastViewType : viewTypeFilters.size() - 1;
        return viewTypeFilters.get(index).creator.view(container);
    }

    @Override
    public int viewTypeFor(T data, int position, int itemCount) {
        lastViewType = filter(data, position, itemCount).viewType;
        return lastViewType;
    }

    @Override
    public int viewTypeCount() {
        return viewTypeFilters.size();
    }


    public static class ViewTypeFilter<T> {
        public Fun3<T, Integer, Integer, Boolean> filter;
        public ViewCreator<T> creator;
        public int viewType;

        public ViewTypeFilter(Fun3<T, Integer, Integer, Boolean> filter, ViewCreator<T> creator, int viewType) {
            this.filter = filter;
            this.creator = creator;
            this.viewType = viewType;
        }

        public boolean canProcess(T data, int position, int itemCount) {
            return filter.call(data, position, itemCount);
        }

    }
}
