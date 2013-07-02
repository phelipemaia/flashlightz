package com.aiam.flashlightz;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.aiam.flashlightz.R;

import com.google.ads.*;

import com.aiam.controller.Flashlight;
import com.google.ads.mediation.admob.AdMobAdapterExtras;

public class MainActivity extends Activity {

    private Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View mOnOffButtonView = findViewById(R.id.onOffButton);
        Context context = this;
        PackageManager pm = context.getPackageManager();

        Flashlight fl;
        AdView adView = (AdView) this.findViewById(R.id.adView);
        AdRequest ar = new AdRequest();
        adView.loadAd(ar);

        // if device support camera?
        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Log.e("err", "Device has no camera!");
            fl = new Flashlight(this, mOnOffButtonView);
        } else {
            camera = Camera.open();
            fl = new Flashlight(this, mOnOffButtonView, camera);
        }

        fl.attachEvents();

    }
}
