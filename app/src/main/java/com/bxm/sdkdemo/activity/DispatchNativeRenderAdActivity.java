package com.bxm.sdkdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bxm.sdkdemo.Constants;
import com.bxm.sdkdemo.R;
import com.bxm.sdkdemo.ToastUtils;
import com.dhcw.sdk.BDAdvanceNativeRenderAd;
import com.dhcw.sdk.BDAdvanceNativeRenderItem;
import com.dhcw.sdk.BDAdvanceNativeRenderListener;

import java.util.ArrayList;
import java.util.List;

public class DispatchNativeRenderAdActivity extends Activity implements BDAdvanceNativeRenderListener, View.OnClickListener {

    private BDAdvanceNativeRenderItem bdAdvanceNativeRenderItem;

    private FrameLayout advanceNativeAdContainer;
    private Button feedButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_native_render_ad);
        advanceNativeAdContainer = findViewById(R.id.advance_native_ad_container);

        findViewById(R.id.bt_load_express_ad).setOnClickListener(this);
        feedButton = findViewById(R.id.bt_feed_ad);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt_load_express_ad) {
            loadAd();
        }
    }

    private void loadAd() {
        BDAdvanceNativeRenderAd bdAdvanceFeedAd = new BDAdvanceNativeRenderAd(this, advanceNativeAdContainer, Constants.NATIVE_RENDER_AD_SPOT_ID);
        bdAdvanceFeedAd.setBdAdvanceNativeRenderListener(this);
        bdAdvanceFeedAd.loadAD();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (bdAdvanceNativeRenderItem != null) {
            bdAdvanceNativeRenderItem.resume();
        }
    }

    @Override
    public void onLoadAd(List<BDAdvanceNativeRenderItem> advanceFeedItemList) {
        ToastUtils.showToast(DispatchNativeRenderAdActivity.this, "demo : onLoadAd");
        bdAdvanceNativeRenderItem = advanceFeedItemList.get(0);
        setFeedAd(bdAdvanceNativeRenderItem);
        bindListener(bdAdvanceNativeRenderItem);
    }

    private void bindListener(final BDAdvanceNativeRenderItem bdAdvanceFeedItem) {
        List<View> clickViewList = new ArrayList<>();
        clickViewList.add(feedButton);
        bdAdvanceFeedItem.registerViewForInteraction(advanceNativeAdContainer, clickViewList, new AdInteractionListener() {
            @Override
            public void onAdClicked(View view) {
                ToastUtils.showToast(DispatchNativeRenderAdActivity.this, "demo : onAdClicked");
            }

            @Override
            public void onAdShow() {
                ToastUtils.showToast(DispatchNativeRenderAdActivity.this, "demo : onAdShow");
            }

            @Override
            public void onDeeplinkCallback(boolean isSuccess) {
                ToastUtils.showToast(DispatchNativeRenderAdActivity.this, "demo : onDeeplinkCallback");
            }
        });

    }

    @Override
    public void onAdFailed(int code, String errMsg) {
        ToastUtils.showToast(DispatchNativeRenderAdActivity.this, "demo : onAdFailed:"+code+","+errMsg);
    }


    private void setFeedAd(BDAdvanceNativeRenderItem bdAdvanceFeedItem) {

        advanceNativeAdContainer.removeAllViews();

        View inflateView = LayoutInflater.from(this).inflate(R.layout.layout_native_express_view_one, advanceNativeAdContainer);

        ImageView ivExpressAd = inflateView.findViewById(R.id.bxm_iv_express_ad);
        TextView tvExpressTitle = inflateView.findViewById(R.id.bxm_tv_express_title);
        TextView tvExpressSubTitle = inflateView.findViewById(R.id.bxm_tv_express_subtitle);

        Glide.with(this).load(bdAdvanceFeedItem.getImageList().get(0)).into(ivExpressAd);
        tvExpressTitle.setText(bdAdvanceFeedItem.getDescription());
        tvExpressSubTitle.setText(bdAdvanceFeedItem.getTitle());

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bdAdvanceNativeRenderItem != null) {
            bdAdvanceNativeRenderItem.destroy();
        }
    }

}
