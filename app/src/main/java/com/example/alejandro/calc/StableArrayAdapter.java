package com.example.alejandro.calc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StableArrayAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<String> names;
    private ArrayList<Double> prices;

    public StableArrayAdapter(Context context, ArrayList<String> names,
                              ArrayList<Double> prices) {
        super(context, -1, names);
        this.context = context;
        this.names = names;
        this.prices = prices;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_layout, parent, false);
        TextView nameView = (TextView) rowView.findViewById(R.id.itemName);
        TextView priceView = (TextView) rowView.findViewById(R.id.itemPrice);

        nameView.setText(names.get(position));
        priceView.setText(prices.get(position).toString());

        return rowView;
    }

}
