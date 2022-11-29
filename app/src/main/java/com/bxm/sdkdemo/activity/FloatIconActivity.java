package com.bxm.sdkdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.bxm.sdkdemo.Constants;
import com.bxm.sdkdemo.R;
import com.bxm.sdkdemo.ToastUtils;
import com.dhcw.sdk.BDAdvanceFloatIconAd;
import com.dhcw.sdk.BDAdvanceFloatIconListener;

public class FloatIconActivity extends Activity implements View.OnClickListener {

    private FrameLayout adContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_icon_ad);
        findViewById(R.id.bt_show_float_ad).setOnClickListener(this);
        adContainer = findViewById(R.id.fl_ad_container);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt_show_float_ad) {
            loadAd();
        }
    }

    BDAdvanceFloatIconAd bdAdvanceFloatIconAd;

    private void loadAd() {
        bdAdvanceFloatIconAd = new BDAdvanceFloatIconAd(this, adContainer, Constants.FLOAT_AD_SPOT_ID);
        bdAdvanceFloatIconAd.setBdAdvanceFloatIconListener(new BDAdvanceFloatIconListener() {
            @Override
            public void onActivityClosed() {
                ToastUtils.showToast(FloatIconActivity.this, "demo : onActivityClosed");
            }

            @Override
            public void onAdShow() {
                ToastUtils.showToast(FloatIconActivity.this, "demo : onAdShow");
            }

            @Override
            public void onAdFailed(int code, String errMsg) {
                ToastUtils.showToast(FloatIconActivity.this, "demo : onAdFailed:"+code+","+errMsg);
            }

            @Override
            public void onAdClicked() {
                ToastUtils.showToast(FloatIconActivity.this, "demo : onAdClicked");
            }

            @Override
            public void onDeeplinkCallback(boolean isSuccess) {
                ToastUtils.showToast(FloatIconActivity.this, "demo : onDeeplinkCallback");
            }
        });

        bdAdvanceFloatIconAd.loadAd();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bdAdvanceFloatIconAd != null) {
            bdAdvanceFloatIconAd.destroyAd();
            bdAdvanceFloatIconAd = null;
        }
    }
}
