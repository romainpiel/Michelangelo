package com.romainpiel.michelangelo.sample;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.romainpiel.michelangelo.AfterInflate;
import com.romainpiel.michelangelo.InflateLayout;

import butterknife.InjectView;

/**
 * Michelangelo
 * romainpiel
 * 21/01/2014
 */
@InflateLayout(R.layout.custom_view)
public class MyCustomView extends FrameLayout {

    @InjectView(R.id.my_text_view) TextView myTextView;

    public MyCustomView(Context context) {
        super(context);
    }

    public void setText(CharSequence text) {
        myTextView.setText(text);
    }

    @AfterInflate
    public void showToast() {
        Toast.makeText(getContext(), "view inflated!", Toast.LENGTH_SHORT).show();
    }
}
