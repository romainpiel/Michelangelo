package com.romainpiel.michelangelo.sample;

import android.app.ListActivity;
import android.os.Bundle;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Painting[] paintings = new Painting[]{
                new Painting(R.drawable.the_drunkenness_of_noah, "The Drunkenness of Noah"),
                new Painting(R.drawable.the_deluge, "The Deluge"),
                new Painting(R.drawable.the_creation_of_adam, "The Creation of Adam"),
                new Painting(R.drawable.the_first_day_of_creation, "The First day of Creation"),
                new Painting(R.drawable.ignudo, "Ignudo"),
                new Painting(R.drawable.the_libyan_sibyl, "The Libyan Sibyl"),
                new Painting(R.drawable.the_prophet_jeremiah, "The Prophet Jeremiah")
        };

        PaintingAdapter adapter = new PaintingAdapter(this, paintings);
        setListAdapter(adapter);
    }

}
