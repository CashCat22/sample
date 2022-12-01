//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cashcat.sdkdemo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

public class WeakHandler extends Handler {
    private final WeakReference<IHandler> mRef;

    public WeakHandler(IHandler handler) {
        this.mRef = new WeakReference<>(handler);
    }

    public WeakHandler(Looper looper, IHandler handler) {
        super(looper);
        this.mRef = new WeakReference<>(handler);
    }

    public void handleMessage(Message msg) {
        IHandler handler = this.mRef.get();
        if (handler != null && msg != null) {
            handler.handleMsg(msg);
        }
    }

    public interface IHandler {
        void handleMsg(Message var1);
    }
}
