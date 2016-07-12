package com.benny.library.autoadapter.viewholder;

/**
 * Created by benny on 7/8/16.
 */

public class DataGetter<T> {
    final public T previous;
    final public T next;
    final public T data;

    public DataGetter(T previous, T data, T next) {
        this.previous = previous;
        this.next = next;
        this.data = data;
    }
}
