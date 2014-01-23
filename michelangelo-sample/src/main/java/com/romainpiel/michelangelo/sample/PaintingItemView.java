package com.romainpiel.michelangelo.sample;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.romainpiel.michelangelo.InflateLayout;
import com.romainpiel.michelangelo.InjectViews;

import butterknife.InjectView;

/**
 * Michelangelo
 * romainpiel
 * 21/01/2014
 */
@InflateLayout(R.layout.item_painting)
@InjectViews
public class PaintingItemView extends LinearLayout {

    @InjectView(R.id.image) ImageView image;
    @InjectView(R.id.title) TextView title;

    public PaintingItemView(Context context) {
        super(context);
        setOrientation(HORIZONTAL);
    }

    public void bind(Painting painting) {
        image.setImageResource(painting.getDrawableResId());
        title.setText(painting.getTitle());
    }
}
