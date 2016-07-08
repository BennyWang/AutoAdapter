package com.benny.library.autoadapter.viewholder;

/**
 * Created by benny on 7/8/16.
 */

public class DataGetter<T> {
    public T previous;
    public T next;
    public T data;

    public DataGetter(T previous, T data, T next) {
        this.previous = previous;
        this.next = next;
        this.data = data;
    }
}
