package com.example.villagetravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.villagetravel.R;

public class Hotels extends AppCompatActivity {

    ImageView image1, image2, image3, image4, image5, image6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        image1 = findViewById(R.id.imageView_1);
        image2 = findViewById(R.id.imageView_2);
        image3 = findViewById(R.id.imageView_3);
        image4 = findViewById(R.id.imageView_4);
        image5 = findViewById(R.id.imageView_5);
        image6 = findViewById(R.id.imageView_6);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent continueIntent = new Intent(Hotels.this, Available.class);
                continueIntent.putExtra("val", "1");
                startActivity(continueIntent);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(Hotels.this, Available.class);
                continueIntent.putExtra("val", "2");
                startActivity(continueIntent);
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(Hotels.this, Available.class);
                continueIntent.putExtra("val", "3");
                startActivity(continueIntent);
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(Hotels.this, Available.class);
                continueIntent.putExtra("val", "4");
                startActivity(continueIntent);
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(Hotels.this, Available.class);
                continueIntent.putExtra("val", "5");
                startActivity(continueIntent);
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(Hotels.this, Available.class);
                continueIntent.putExtra("val", "6");
                startActivity(continueIntent);
            }
        });
    }
}
