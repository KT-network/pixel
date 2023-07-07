package com.kt.ks_pixel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kt.ks_pixel.KsPixel.PixelView;

public class MainActivity extends AppCompatActivity {

    PixelView pixelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pixelView = findViewById(R.id.pixel);

    }
}