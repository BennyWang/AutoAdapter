package com.benny.library.autoadapter.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by benny on 2/26/16.
 */

public class SimpleAdapterItemAccessor<T> implements IAdapterItemAccessor<T> {
    private List<T> data;
    IDataSetChangedNotifier notifier;

    public SimpleAdapterItemAccessor() {
        data = new ArrayList<T>();
    }

    public SimpleAdapterItemAccessor(List<T> data) {
        this.data = data;
    }

    public void update(List<T> data) {
        this.data = data;
        notifier.notifyDataSetChanged();
    }

    public void setDataSetChangedNotifier(IDataSetChangedNotifier notifier) {
        this.notifier = notifier;
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
