package com.aiam.controller;

import android.content.Context;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.aiam.flashlightz.R;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Phelipe on 26/06/13.
 */
public class Flashlight {
    private ImageButton mOnOffButton;
    private View mOnOffButtonView;
    private boolean on;
    private Camera camera;
    private Context context;

    public Flashlight(Context context, View mOnOffButtonView) {
        this.mOnOffButtonView = mOnOffButtonView;
        this.mOnOffButton = (ImageButton) mOnOffButtonView;
        this.on = false;
        this.context = context;
    }

    public Flashlight(Context context, View mOnOffButtonView, Camera camera) {
        this.mOnOffButtonView = mOnOffButtonView;
        this.mOnOffButton = (ImageButton) mOnOffButtonView;
        this.on = false;
        this.camera = camera;
        this.context = context;
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
        MediaPlayer mp = MediaPlayer.create(context, R.raw.on);
        mp.start();
        if (camera != null) {
            final Camera.Parameters p = camera.getParameters();
            p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(p);
            camera.startPreview();
        }
        final ImageView imageView = (ImageView) mOnOffButtonView;
        imageView.setImageResource(R.drawable.on);
    }

    protected void turnOff() {
        MediaPlayer mp = MediaPlayer.create(context, R.raw.off);
        mp.start();
        if (camera != null) {
            final Camera.Parameters p = camera.getParameters();
            p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(p);
            camera.stopPreview();
        }
        final ImageView imageView = (ImageView) mOnOffButtonView;
        imageView.setImageResource(R.drawable.off);
    }
}
