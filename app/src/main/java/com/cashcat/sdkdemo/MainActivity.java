package com.cashcat.sdkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cashcat.sdkdemo.activity.DispatchBannerAdActivity;
import com.cashcat.sdkdemo.activity.DispatchInteractionActivity;
import com.cashcat.sdkdemo.activity.DispatchNativeRenderAdActivity;
import com.cashcat.sdkdemo.activity.DispatchRewardVideoActivity;
import com.cashcat.sdkdemo.activity.DispatchSplashAdActivity;
import com.cashcat.sdkdemo.activity.FloatIconActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView sdkVersion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_main);
        initView();
        sdkVersion.setText("V" + BuildConfig.VERSION_NAME);
    }

    private void initView() {
        sdkVersion = findViewById(R.id.tv_ver);
        findViewById(R.id.bt_float_ad).setOnClickListener(this);
        findViewById(R.id.bt_reward_video_ad).setOnClickListener(this);
        findViewById(R.id.bt_banner_ad).setOnClickListener(this);
        findViewById(R.id.bt_splash_ad).setOnClickListener(this);
        findViewById(R.id.bt_interaction_ad).setOnClickListener(this);
        findViewById(R.id.bt_feed_ad).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        Intent intent;
        switch (viewId) {
            case R.id.bt_float_ad:
                intent = new Intent(this, FloatIconActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_reward_video_ad:
                intent = new Intent(this, DispatchRewardVideoActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_banner_ad:
                intent = new Intent(this, DispatchBannerAdActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_splash_ad:
                intent = new Intent(this, DispatchSplashAdActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_interaction_ad:
                intent = new Intent(this, DispatchInteractionActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_feed_ad:
                intent = new Intent(this, DispatchNativeRenderAdActivity.class);
                startActivity(intent);
                break;
        }
    }
}