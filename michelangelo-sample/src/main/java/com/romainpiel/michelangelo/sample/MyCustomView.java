package com.romainpiel.michelangelo.sample;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.romainpiel.michelangelo.AfterInflate;
import com.romainpiel.michelangelo.InflateLayout;

/**
 * Michelangelo
 * romainpiel
 * 21/01/2014
 */
@InflateLayout(R.layout.custom_view)
public class MyCustomView extends FrameLayout {

    public MyCustomView(Context context) {
        super(context);
    }

    @AfterInflate
    public void showToast() {
        Toast.makeText(getContext(), "view inflated!", Toast.LENGTH_SHORT).show();
    }
}
