package com.aiam.flashlightz;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.ads.*;

import com.aiam.controller.Flashlight;

public class MainActivity extends Activity {

    private Camera camera;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adView = new AdView(this, AdSize.BANNER, "123");
        setContentView(R.layout.activity_main);
        View mOnOffButtonView = findViewById(R.id.onOffButton);
        Context context = this;
        PackageManager pm = context.getPackageManager();

        Flashlight fl;
        LinearLayout layout = (LinearLayout)findViewById(R.id.mainLayout);
        layout.addView(adView);
        adView.loadAd(new AdRequest());

        // if device support camera?
        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Log.e("err", "Device has no camera!");
            fl = new Flashlight(mOnOffButtonView);
        } else {
            camera = Camera.open();
            fl = new Flashlight(mOnOffButtonView, camera);
        }

        fl.attachEvents();

    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
    
}
