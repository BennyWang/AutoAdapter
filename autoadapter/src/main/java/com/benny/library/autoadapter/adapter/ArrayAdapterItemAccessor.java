package com.benny.library.autoadapter.adapter;

import java.util.List;

/**
 * Created by benny on 2/26/16.
 */

public class ArrayAdapterItemAccessor<T> implements IAdapterItemAccessor<T> {
    private List<T> data;

    public ArrayAdapterItemAccessor(List<T> data) {
        this.data = data;
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
