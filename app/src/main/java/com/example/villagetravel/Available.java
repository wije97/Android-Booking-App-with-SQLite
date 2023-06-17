package com.example.villagetravel;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.villagetravel.R;

import org.w3c.dom.Text;


public class Available extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available);

        Button book1 = (Button) findViewById(R.id.book);
        TextView txt = (TextView)findViewById(R.id.textq);
        ImageView img1 = (ImageView)findViewById(R.id.imageView11);
        ImageView img2 = (ImageView)findViewById(R.id.imageView16);
        ImageView img3 = (ImageView)findViewById(R.id.imageView18);

        String no = getIntent().getStringExtra("val");
        int num = Integer.parseInt(no);

        if (num == 1){
            img1.setImageResource(R.drawable.pict01);
            img2.setImageResource(R.drawable.pic11);
            img3.setImageResource(R.drawable.pic12);
        }
        if (num == 2){
            img1.setImageResource(R.drawable.pict02);
            img2.setImageResource(R.drawable.pic21);
            img3.setImageResource(R.drawable.pic22);
        }
        if (num == 3){
            img1.setImageResource(R.drawable.pict03);
            img2.setImageResource(R.drawable.pic31);
            img3.setImageResource(R.drawable.pic32);
        }
        if (num == 4){
            img1.setImageResource(R.drawable.pict04);
            img2.setImageResource(R.drawable.pic41);
            img3.setImageResource(R.drawable.pic42);
        }
        if (num == 5){
            img1.setImageResource(R.drawable.pict05);
            img2.setImageResource(R.drawable.pic51);
            img3.setImageResource(R.drawable.pic52);
        }
        if (num == 6){
            img1.setImageResource(R.drawable.pict06);
            img2.setImageResource(R.drawable.pic61);
            img3.setImageResource(R.drawable.pic62);
        }

        book1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(Available.this, Reservation.class);
                startActivity(continueIntent);
            }
        });
    }
}
