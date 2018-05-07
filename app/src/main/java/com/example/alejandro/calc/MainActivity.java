package com.example.alejandro.calc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView articlesGrid;
    private ListView countCheck;

    String[] names = new String[] {"Cigarros", "Coca", "Galletas", "Huevo",
        "Leche", "Pan", "Sabritas", "Tortillas"};
    Double[] prices = new Double[] {49.00, 11.50, 8.0, 35.50,
        20.0, 6.50, 12.0, 13.0};
    ArrayList<String> namesList = new ArrayList<>();
    ArrayList<Double> pricesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countCheck = findViewById(R.id.countCheck);

        // Names and Prices arrays are the same length
        for(int i=0; i<names.length; ++i) {
            namesList.add(names[i]);
            pricesList.add(prices[i]);
        }

        final StableArrayAdapter adapter;
        adapter = new StableArrayAdapter(MainActivity.this, namesList, pricesList);
        countCheck.setAdapter(adapter);

        countCheck.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                namesList.remove(item);
                                adapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });
            }

        });

        articlesGrid = findViewById(R.id.articlesGrid);
        articlesGrid.setAdapter(new ImageAdapter(this));

        articlesGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                // Send intent to SingleViewActivity
                Intent i = new Intent(getApplicationContext(), FullViewActivity.class);

                // Pass image index
                i.putExtra("id", position);
                startActivity(i);
            }
        });

    }
}
