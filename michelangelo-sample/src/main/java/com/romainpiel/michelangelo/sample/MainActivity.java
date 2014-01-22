package com.romainpiel.michelangelo.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.romainpiel.michelangelo.Michelangelo;
import com.romainpiel.michelangelo.OnViewChangedListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyCustomView view = Michelangelo.inflate(this, MyCustomView.class, new OnViewChangedListener<MyCustomView>() {
            @Override
            public void onViewChanged(MyCustomView view) {
                ((TextView) view.findViewById(R.id.my_text_view)).setText("hey!");
            }
        });

        setContentView(view);
    }

}
