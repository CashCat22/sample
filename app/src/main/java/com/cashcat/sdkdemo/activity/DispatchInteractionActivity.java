package com.cashcat.sdkdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.cashcat.sdkdemo.Constants;
import com.cashcat.sdkdemo.R;
import com.cashcat.sdkdemo.ToastUtils;
import com.dhcw.sdk.BDAdvanceInteractionAd;
import com.dhcw.sdk.BDAdvanceInteractionListener;

public class DispatchInteractionActivity extends Activity implements View.OnClickListener {

    private BDAdvanceInteractionAd bdAdvanceInteractionAd;
    private boolean isReady;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_interaction);
        findViewById(R.id.bt_load_interaction_ad).setOnClickListener(this);
        findViewById(R.id.bt_show_interaction_ad).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt_load_interaction_ad) {
            loadAd();
        } else if (view.getId() == R.id.bt_show_interaction_ad) {
            if (isReady) {
                bdAdvanceInteractionAd.showAd();
            }
        }
    }

    private void loadAd() {
        bdAdvanceInteractionAd = new BDAdvanceInteractionAd(this, Constants.INTERACTION_AD_SPOT_ID);
        bdAdvanceInteractionAd.setBdAdvanceInteractionListener(new BDAdvanceInteractionListener() {
            @Override
            public void onAdClose() {
                ToastUtils.showToast(DispatchInteractionActivity.this, "demo : onAdClose");
            }

            @Override
            public void onAdSuccess() {
                ToastUtils.showToast(DispatchInteractionActivity.this, "demo : onAdSuccess");
                isReady = true;
            }

            @Override
            public void onAdShow() {
                ToastUtils.showToast(DispatchInteractionActivity.this, "demo : onAdShow");
            }

            @Override
            public void onAdFailed(int code, String errMsg) {
                ToastUtils.showToast(DispatchInteractionActivity.this, "demo : onAdFailed:"+code+","+errMsg);
                isReady = false;
            }

            @Override
            public void onAdClicked() {
                ToastUtils.showToast(DispatchInteractionActivity.this, "demo : onAdClicked");
            }

            @Override
            public void onDeeplinkCallback(boolean isSuccess) {
                ToastUtils.showToast(DispatchInteractionActivity.this, "demo : onDeeplinkCallback");
            }
        });


        bdAdvanceInteractionAd.loadAD();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bdAdvanceInteractionAd != null) {
            bdAdvanceInteractionAd.destroy();
        }
    }
}
