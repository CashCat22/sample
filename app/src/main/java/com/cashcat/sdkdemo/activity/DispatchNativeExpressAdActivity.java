package com.cashcat.sdkdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.cashcat.sdkdemo.Constants;
import com.cashcat.sdkdemo.R;
import com.cashcat.sdkdemo.ToastUtils;
import com.dhcw.sdk.BDAdvanceNativeExpressAd;
import com.dhcw.sdk.BDAdvanceNativeExpressAdItem;
import com.dhcw.sdk.BDAdvanceNativeExpressListener;

import java.util.List;

public class DispatchNativeExpressAdActivity extends Activity implements View.OnClickListener {

    private FrameLayout flContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_express_ad);
        flContainer = findViewById(R.id.fl_ad_container);
        findViewById(R.id.bt_load_express_ad).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt_load_express_ad) {
            loadAd();
        }
    }

    private void loadAd() {
        final BDAdvanceNativeExpressAd bdAdvanceNativeExpressAd = new BDAdvanceNativeExpressAd(this, Constants.Native_AD_SPOT_ID);
        bdAdvanceNativeExpressAd.setAdCount(1);
        bdAdvanceNativeExpressAd.registerBxmAdvanceExpressListener(new BDAdvanceNativeExpressListener() {
            @Override
            public void onLoadExpressList(List<BDAdvanceNativeExpressAdItem> list) {
                ToastUtils.showToast(getApplicationContext(), "demo : onLoadExpressList");
                if (list.size() > 0) {
                    BDAdvanceNativeExpressAdItem bdAdvanceNativeExpressAdItem = list.get(0);
                    bdAdvanceNativeExpressAdItem.render();// csj
                }
            }

            @Override
            public void onAdClose(View view) {
                ToastUtils.showToast(getApplicationContext(), "demo : onAdClose");
            }

            @Override
            public void onAdRenderFailed(View view) {
                ToastUtils.showToast(getApplicationContext(), "demo : onAdRenderFailed");
            }

            @Override
            public void onAdRenderSuccess(View view, float width, float height) {
                ToastUtils.showToast(getApplicationContext(), "demo : onAdRenderSuccess");
                flContainer.removeAllViews();
                flContainer.setVisibility(View.VISIBLE);
                flContainer.addView(view);
            }

            @Override
            public void onAdShow() {
                ToastUtils.showToast(getApplicationContext(), "demo : onAdShow");
            }

            @Override
            public void onAdFailed(int code, String errMsg) {
                ToastUtils.showToast(getApplicationContext(), "demo : onAdFailed:" + code + "," + errMsg);
            }

            @Override
            public void onAdClicked() {
                ToastUtils.showToast(getApplicationContext(), "demo : onAdClicked");
            }

            @Override
            public void onDeeplinkCallback(boolean isSuccess) {
                ToastUtils.showToast(getApplicationContext(), "demo : onDeeplinkCallback");
            }


        });
        bdAdvanceNativeExpressAd.loadAD();
    }
}
