package com.benny.library.autoadapter;

import com.benny.library.autoadapter.listener.DataSetChangedNotifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benny on 2/26/16.
 */

public class SimpleAdapterItemAccessor<T> implements IAdapterItemAccessor<T> {
    private List<T> data;
    DataSetChangedNotifier changedNotifier;

    public SimpleAdapterItemAccessor() {
        data = new ArrayList<T>();
    }

    public SimpleAdapterItemAccessor(List<T> data) {
        this.data = data;
    }

    public void update(List<T> data) {
        this.data = data;
        if(changedNotifier != null) changedNotifier.notifyDataSetChanged();
    }

    public void setDataSetChangedNotifier(DataSetChangedNotifier changedNotifier) {
        this.changedNotifier = changedNotifier;
    }

    public int size() {
        return data.size();
    }

    public T get(int position) {
        return data.get(position);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }
}
