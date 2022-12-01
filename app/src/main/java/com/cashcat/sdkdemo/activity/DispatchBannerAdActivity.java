package com.cashcat.sdkdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.cashcat.sdkdemo.Constants;
import com.cashcat.sdkdemo.R;
import com.cashcat.sdkdemo.ToastUtils;
import com.dhcw.sdk.BDAdvanceBannerAd;
import com.dhcw.sdk.BDAdvanceBannerListener;

public class DispatchBannerAdActivity extends Activity implements View.OnClickListener {

    private ViewGroup container;
    private BDAdvanceBannerAd bdAdvanceBannerAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_banner_ad);
        container = findViewById(R.id.fl_ad_container);

        findViewById(R.id.bt_ad_load).setOnClickListener(this);
        findViewById(R.id.bt_show_banner_ad).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt_ad_load) {
            loadBannerAd();
        } else if (view.getId() == R.id.bt_show_banner_ad) {
            if (bdAdvanceBannerAd != null) {
                bdAdvanceBannerAd.showAd();
            }
        }
    }

    private void loadBannerAd() {
        bdAdvanceBannerAd = new BDAdvanceBannerAd(this, container, Constants.BANNER_AD_SPOT_ID);
        bdAdvanceBannerAd.setCsjAcceptedSize(0, 0);
        bdAdvanceBannerAd.setBDAdvanceBannerListener(new BDAdvanceBannerListener() {
            @Override
            public void onDislike() {
                ToastUtils.showToast(DispatchBannerAdActivity.this, "demo : onDislike");
                container.setVisibility(View.GONE);
            }

            @Override
            public void onAdSuccess() {
                ToastUtils.showToast(DispatchBannerAdActivity.this, "demo : onAdSuccess");
//                bdAdvanceBannerAd.showAd();
            }

            @Override
            public void onAdShow() {
                ToastUtils.showToast(DispatchBannerAdActivity.this, "demo : onAdShow");
            }

            @Override
            public void onAdFailed(int code, String errMsg) {
                ToastUtils.showToast(DispatchBannerAdActivity.this, "demo : onAdFailed:"+code+","+errMsg);
            }

            @Override
            public void onAdClicked() {
                ToastUtils.showToast(DispatchBannerAdActivity.this, "demo : onAdClicked");
            }

            @Override
            public void onDeeplinkCallback(boolean isSuccess) {
                ToastUtils.showToast(DispatchBannerAdActivity.this, "demo : onDeeplinkCallback");
            }
        });


        bdAdvanceBannerAd.loadAD();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bdAdvanceBannerAd != null) bdAdvanceBannerAd.destroy();
    }
}
