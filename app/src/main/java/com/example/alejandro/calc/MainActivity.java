package com.example.alejandro.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private GridView articlesGrid;
    private ListView countCheck;
    private TextView totalDisplay;

    private Button oneHundredButton;
    private Button twoHundredButton;
    private Button fiveHundredButton;
    private Button resetButton;

    private String[] names = new String[] {"Cigarros", "Coca", "Galletas", "Huevo",
        "Leche", "Pan", "Sabritas", "Tortillas"};
    private Double[] prices = new Double[] {49.00, 11.50, 8.0, 35.50,
        20.0, 6.50, 12.0, 13.0};

    private ArrayList<String> namesList = new ArrayList<>();
    private ArrayList<Double> pricesList = new ArrayList<>();

    private StableArrayAdapter adapter;

    Double total = 0.0;

    void computeTotal() {
        total = 0.0;
        for(Double d : pricesList)
            total += d;
        totalDisplay.setText(String.format(Locale.ENGLISH, "$ %.2f ", total));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalDisplay = findViewById(R.id.totalDisplay);

        countCheck = findViewById(R.id.countCheck);

        adapter = new StableArrayAdapter(MainActivity.this, namesList, pricesList);
        countCheck.setAdapter(adapter);

        countCheck.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    final int position, long id) {
                view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                namesList.remove(position);
                                pricesList.remove(position);
                                adapter.notifyDataSetChanged();
                                computeTotal();
                                view.setAlpha(1);
                            }
                        });
            }

        });

        articlesGrid = findViewById(R.id.articlesGrid);
        articlesGrid.setAdapter(new ImageAdapter(this));

        articlesGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                if(namesList.contains(names[position])) {
                    int currPos = namesList.indexOf(names[position]);
                    Double currPrice = pricesList.get(currPos);
                    pricesList.set(currPos, currPrice+prices[position]);
                } else {
                    namesList.add(names[position]);
                    pricesList.add(prices[position]);
                }
                adapter.notifyDataSetChanged();
                computeTotal();
            }
        });

        oneHundredButton = (Button) findViewById(R.id.oneHundredButton);
        twoHundredButton = (Button) findViewById(R.id.twoHundredButton);
        fiveHundredButton = (Button) findViewById(R.id.fiveHundredButton);
        resetButton = (Button) findViewById(R.id.resetButton);

        oneHundredButton.setOnClickListener(this);
        twoHundredButton.setOnClickListener(this);
        fiveHundredButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String text;
        SpannableStringBuilder biggerText;

        switch (view.getId()) {
            case R.id.oneHundredButton:
                text = String.format(Locale.ENGLISH,"%.2f",100.0-total);
                biggerText = new SpannableStringBuilder(text);
                biggerText.setSpan(new RelativeSizeSpan(2.00f), 0, text.length(), 0);
                Toast.makeText(MainActivity.this, biggerText, Toast.LENGTH_SHORT).show();
                break;
            case R.id.twoHundredButton:
                text = String.format(Locale.ENGLISH,"%.2f",200.0-total);
                biggerText = new SpannableStringBuilder(text);
                biggerText.setSpan(new RelativeSizeSpan(2.00f), 0, text.length(), 0);
                Toast.makeText(MainActivity.this, biggerText, Toast.LENGTH_SHORT).show();
                break;
            case R.id.fiveHundredButton:
                text = String.format(Locale.ENGLISH,"%.2f",500.0-total);
                biggerText = new SpannableStringBuilder(text);
                biggerText.setSpan(new RelativeSizeSpan(2.00f), 0, text.length(), 0);
                Toast.makeText(MainActivity.this, biggerText, Toast.LENGTH_SHORT).show();
                break;
            case R.id.resetButton:
                text = "Nueva Cuenta";
                biggerText = new SpannableStringBuilder(text);
                biggerText.setSpan(new RelativeSizeSpan(2.00f), 0, text.length(), 0);
                Toast.makeText(MainActivity.this, biggerText, Toast.LENGTH_SHORT).show();
                namesList.clear();
                pricesList.clear();
                adapter.notifyDataSetChanged();
                computeTotal();
            default:
                break;
        }
    }
}
