package com.example.villagetravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.villagetravel.R;

public class LastPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_page);

        Button okbtn = (Button) findViewById(R.id.ok);

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(LastPage.this, MainActivity.class);
                startActivity(continueIntent);
            }
        });
    }
}
