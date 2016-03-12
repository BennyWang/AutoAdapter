package com.benny.library.autoadapter.factory;

import com.benny.library.autoadapter.viewcreator.IViewCreator;
import com.benny.library.autoadapter.viewholder.IViewHolder;
import com.benny.library.autoadapter.viewholder.MockViewHolder;

/**
 * Created by benny on 2/27/16.
 */

public class MockViewHolerFactory<T> implements Factory<IViewHolder<T>> {
    @Override
    public IViewHolder<T> create() {
        return new MockViewHolder<T>();
    }
}
