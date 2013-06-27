package com.aiam.controller;

import android.hardware.Camera;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.aiam.flashlightz.R;

/**
 * Created by Phelipe on 26/06/13.
 */
public class Flashlight {
    private ImageButton mOnOffButton;
    private View mOnOffButtonView;
    private boolean on;
    private Camera camera;

    public Flashlight(View mOnOffButtonView) {
        this.mOnOffButtonView = mOnOffButtonView;
        this.mOnOffButton = (ImageButton) mOnOffButtonView;
        this.on = false;
    }

    public Flashlight(View mOnOffButtonView, Camera camera) {
        this.mOnOffButtonView = mOnOffButtonView;
        this.mOnOffButton = (ImageButton) mOnOffButtonView;
        this.on = false;
        this.camera = camera;
    }

    public void attachEvents() {
        mOnOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                on = !on;
                if (on) {
                    turnOn();
                } else {
                    turnOff();
                }
            }
        });
    }

    protected void turnOn() {
        if (camera != null) {
            final Camera.Parameters p = camera.getParameters();
            p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(p);
            camera.stopPreview();
        }
        final ImageView imageView = (ImageView) mOnOffButtonView;
        imageView.setImageResource(R.drawable.on);
    }

    protected void turnOff() {
        if (camera != null) {
            final Camera.Parameters p = camera.getParameters();
            p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(p);
            camera.startPreview();
        }
        final ImageView imageView = (ImageView) mOnOffButtonView;
        imageView.setImageResource(R.drawable.off);
    }
}
