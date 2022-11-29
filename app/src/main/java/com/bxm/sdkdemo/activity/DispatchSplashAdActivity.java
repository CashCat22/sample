package com.bxm.sdkdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.bxm.sdkdemo.Constants;
import com.bxm.sdkdemo.MainActivity;
import com.bxm.sdkdemo.R;
import com.bxm.sdkdemo.ToastUtils;
import com.bxm.sdkdemo.WeakHandler;
import com.dhcw.sdk.BDAdvanceSplashAd;
import com.dhcw.sdk.BDAdvanceSplashListener;

public class DispatchSplashAdActivity extends Activity implements WeakHandler.IHandler {

    private FrameLayout splashContainer;
    private BDAdvanceSplashAd advanceSplashAd;

    private static final int MSG_GO_MAIN = 1000;
    private boolean canJump;
    private WeakHandler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_splash);
        splashContainer = findViewById(R.id.splash_container);
        mHandler = new WeakHandler(this);
        advanceSplashAd = new BDAdvanceSplashAd(this, Constants.SPLASH_AD_SPOT_ID);
        advanceSplashAd.setSplashListener(new BDAdvanceSplashListener() {
                    @Override
                    public void onClose() {
                        mHandler.sendEmptyMessageDelayed(MSG_GO_MAIN, 500);
                        ToastUtils.showToast(DispatchSplashAdActivity.this, "demo : onClose");
                    }

                    @Override
                    public void onAdSuccess() {
                        ToastUtils.showToast(DispatchSplashAdActivity.this, "demo : onAdSuccess");
                        advanceSplashAd.showAd(splashContainer);
                    }

                    @Override
                    public void onAdShow() {
                        ToastUtils.showToast(DispatchSplashAdActivity.this, "demo : onAdShow");
                    }

                    @Override
                    public void onAdFailed(int code, String errMsg) {
                        ToastUtils.showToast(DispatchSplashAdActivity.this, "demo : onAdFailed:"+code+","+errMsg);
                        goToMainActivity();
                    }

                    @Override
                    public void onAdClicked() {
                        ToastUtils.showToast(DispatchSplashAdActivity.this, "demo : onAdClicked");
                    }

                    @Override
                    public void onDeeplinkCallback(boolean isSuccess) {
                        ToastUtils.showToast(DispatchSplashAdActivity.this, "demo : onDeeplinkCallback");
                    }
                });

        advanceSplashAd.loadAD();
    }

    @Override
    protected void onResume() {
        if (canJump) {
            next();
        }
        canJump = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        canJump = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void handleMsg(Message msg) {
        if (msg.what == MSG_GO_MAIN) {
            next();
        }
    }

    private void next() {
        if (canJump) {
            goToMainActivity();
        } else {
            canJump = true;
        }
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
