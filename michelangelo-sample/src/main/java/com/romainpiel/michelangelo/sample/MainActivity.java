package com.romainpiel.michelangelo.sample;

import android.app.Activity;
import android.os.Bundle;

import com.romainpiel.michelangelo.Michelangelo;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyCustomView view = Michelangelo.inflateAndInject(this, MyCustomView.class);
        view.setText("heya!");

        setContentView(view);
    }

}
