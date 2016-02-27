package com.benny.library.autoadapter.adapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by benny on 2/26/16.
 */

public class SimpleAdapterItemAccessor<T> implements IAdapterItemAccessor<T> {
    private List<T> data;

    public SimpleAdapterItemAccessor(List<T> data) {
        this.data = data;
    }

    public SimpleAdapterItemAccessor(T[] data) {
        this.data = Arrays.asList(data);
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
