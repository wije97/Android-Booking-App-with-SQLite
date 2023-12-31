package com.example.villagetravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.villagetravel.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signupbtn = (Button) findViewById(R.id.button7); //cast signup button
        Button loginbtn = (Button) findViewById(R.id.button5); //cast login button

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(MainActivity.this, SignUp.class);
                startActivity(continueIntent);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(MainActivity.this, LoginPage.class);
                startActivity(continueIntent);
            }
        });
    }
}
