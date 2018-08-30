package com.xinde.baggio.sandbox;

import android.content.Context;

/**
 * author: Shawn
 * time  : 2018/7/6 18:37
 * desc  :
 * update: Shawn 2018/7/6 18:37
 */
public abstract class AbstractGenerator {
    protected Context context;

    public AbstractGenerator(Context context) {
        this.context = context;
    }

    public abstract void run();
}
