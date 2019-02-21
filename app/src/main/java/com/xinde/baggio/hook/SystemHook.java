package com.xinde.baggio.hook;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class SystemHook extends AbstractHook {
    public SystemHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        super(loadPackageParam);
    }

    @Override
    public void run() {

    }
}
