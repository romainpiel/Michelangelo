package com.romainpiel.michelangelo.sample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.romainpiel.michelangelo.Michelangelo;

/**
 * Michelangelo
 * romainpiel
 * 23/01/2014
 */
public class PaintingAdapter extends BaseAdapter {

    private Context context;
    private Painting[] items;

    public PaintingAdapter(Context context, Painting[] items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Painting getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PaintingItemView itemView;
        if (convertView == null) {
            itemView = Michelangelo.build(context, PaintingItemView.class);
        } else {
            itemView = (PaintingItemView) convertView;
        }

        Painting item = getItem(position);
        itemView.bind(item);

        return itemView;

    }
}
