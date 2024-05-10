package com.example.easycreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    Button btn_max;
    Button btn_disney;
    Button btn_win;
    Button btn_product_1;
    Button btn_product_2;
    Button btn_product_3;
    Button btn_product_4;


    private ViewPager viewPager;
    private ImageAdapter adapter;
    private int[] images = {R.drawable.netflix_1, R.drawable.disney_1, R.drawable.max_1, R.drawable.win_1};

    Button btn_ingre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        adapter = new ImageAdapter(this, images);
        viewPager.setAdapter(adapter);

        btn_ingre = findViewById(R.id.btn_ingre);

        btn_ingre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
            }
        });

        btn_max = findViewById(R.id.btn_max);
        btn_disney = findViewById(R.id.btn_disney);
        btn_win = findViewById(R.id.btn_win);
        btn_product_1 = findViewById(R.id.btn_product_1);
        btn_product_2 = findViewById(R.id.btn_product_2);
        btn_product_3 = findViewById(R.id.btn_product_3);
        btn_product_4 = findViewById(R.id.btn_product_4);

        btn_max.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity7.class));
            }
        });

        btn_disney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity6.class));
            }
        });

        btn_win.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity10.class));
            }
        });

        btn_product_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity4.class));
            }
        });

        btn_product_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity4.class));
            }
        });

        btn_product_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity4.class));
            }
        });

        btn_product_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity4.class));
            }
        });

    }
}