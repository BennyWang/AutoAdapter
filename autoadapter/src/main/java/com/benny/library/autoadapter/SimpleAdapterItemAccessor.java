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
        this.data = new ArrayList<T>(data);
        notifyDataSetChanged();
    }

    public void append(T item) {
        data.add(item);
    }

    public void append(List<T> item) {
        data.addAll(item);
    }

    public void prepend(T item) {
        data.add(0, item);
    }

    public void prepend(List<T> item) {
        data.addAll(0, item);
    }

    public void remove(int position) {
        data.remove(position);
    }

    public void remove(T t) {
        data.remove(t);
    }

    public void setDataSetChangedNotifier(DataSetChangedNotifier changedNotifier) {
        this.changedNotifier = changedNotifier;
    }

    public void notifyDataSetChanged() {
        if(changedNotifier != null) changedNotifier.notifyDataSetChanged();
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

    public List<T> getAll() {
        return data;
    }
}
