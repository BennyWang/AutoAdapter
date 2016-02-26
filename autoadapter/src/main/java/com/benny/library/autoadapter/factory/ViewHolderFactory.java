package com.benny.library.autoadapter.factory;

import android.view.View;

/**
 * Created by benny on 2/15/16.
 */

public class ViewHolderFactory {

    public static <T> T create(View view, Class<T> vhClass) {
        try {
            return vhClass.getConstructor(View.class).newInstance(view);
        }
        catch (Exception e) {
            throw new RuntimeException("unknown viewholder type");
        }
    }
}
