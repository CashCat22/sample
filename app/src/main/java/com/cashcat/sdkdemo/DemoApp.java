package com.cashcat.sdkdemo;

import android.app.Application;

import com.dhcw.sdk.manager.BDAdvanceConfig;
import com.dhcw.sdk.manager.BDManager;


public class DemoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BDAdvanceConfig.getInstance()
                .setAppName("CashCatSDKDemo")
                .setDebug(true);
        BDManager.getStance().init(this, "0c3b8296216a4cb583d9a5791053b580");
    }

}
