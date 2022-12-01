package com.cashcat.sdkdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.cashcat.sdkdemo.Constants;
import com.cashcat.sdkdemo.R;
import com.cashcat.sdkdemo.ToastUtils;
import com.dhcw.sdk.BDAdvanceRewardAd;
import com.dhcw.sdk.BDAdvanceRewardListener;

public class DispatchRewardVideoActivity extends Activity implements View.OnClickListener {
    BDAdvanceRewardAd bdAdvanceRewardAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_reward_video_ad);
        findViewById(R.id.bt_load_reward_ad).setOnClickListener(this);
        findViewById(R.id.bt_show_reward_ad).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.bt_load_reward_ad:
                loadAd();
                break;
            case R.id.bt_show_reward_ad:
                showAd();
                break;

        }
    }

    private void loadAd() {
        bdAdvanceRewardAd = new BDAdvanceRewardAd(this, Constants.REWARD_AD_SPOT_ID);
        bdAdvanceRewardAd.setBdAdvanceRewardListener(new BDAdvanceRewardListener() {
            @Override
            public void onAdLoad() {
                ToastUtils.showToast(DispatchRewardVideoActivity.this, "demo : onAdLoad");
            }

            @Override
            public void onPlayCompleted() {
                ToastUtils.showToast(DispatchRewardVideoActivity.this, "demo : onPlayCompleted");
            }


            @Override
            public void onAdClose() {
                ToastUtils.showToast(DispatchRewardVideoActivity.this, "demo : onAdClose");
            }

            /**
             * 奖励回调
             */
            @Override
            public void onReward() {
                ToastUtils.showToast(DispatchRewardVideoActivity.this, "demo : onReward");
            }

            @Override
            public void onAdShow() {
                ToastUtils.showToast(DispatchRewardVideoActivity.this, "demo : onAdShow");
            }

            @Override
            public void onAdFailed(int code, String errMsg) {
                ToastUtils.showToast(DispatchRewardVideoActivity.this, "demo : onAdFailed:"+code+","+errMsg);
            }

            @Override
            public void onAdClicked() {
                ToastUtils.showToast(DispatchRewardVideoActivity.this, "demo : onAdClicked");
            }

            @Override
            public void onDeeplinkCallback(boolean isSuccess) {
                ToastUtils.showToast(DispatchRewardVideoActivity.this, "demo : onDeeplinkCallback");
            }

        });
        bdAdvanceRewardAd.loadAD();
    }

    private void showAd() {
        if (bdAdvanceRewardAd != null && bdAdvanceRewardAd.isAdReady()) {
            bdAdvanceRewardAd.showAd();
        }
    }
}
