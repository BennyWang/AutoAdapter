package com.benny.library.autoadapter.factory;

import com.benny.library.autoadapter.viewcreator.IViewCreator;

/**
 * Created by benny on 2/27/16.
 */

public interface Factory<T> {
    T create();
}
