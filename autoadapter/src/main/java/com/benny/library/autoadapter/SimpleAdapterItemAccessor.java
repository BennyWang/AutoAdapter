package com.benny.library.autoadapter;

import com.benny.library.autoadapter.listener.DataSetChangedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benny on 2/26/16.
 */

public class SimpleAdapterItemAccessor<T> implements IAdapterItemAccessor<T> {
    private List<T> data;
    DataSetChangedListener listener;

    public SimpleAdapterItemAccessor() {
        data = new ArrayList<T>();
    }

    public SimpleAdapterItemAccessor(List<T> data) {
        this.data = data;
    }

    public void update(List<T> data) {
        this.data = data;
        if(listener != null) listener.onDataSetChanged();
    }

    public void setDataSetChangedListener(DataSetChangedListener listener) {
        this.listener = listener;
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
