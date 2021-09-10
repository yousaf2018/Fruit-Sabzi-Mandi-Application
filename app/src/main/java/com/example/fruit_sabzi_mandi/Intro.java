package com.example.fruit_sabzi_mandi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Intro extends AppCompatActivity {
    Button btnUrdu;
    Button btnEnglish;
    Button commissionDashboard;
    Button farmerDashboard;
    ;@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        commissionDashboard = (Button) findViewById(R.id.commissionDashboard);
        farmerDashboard = (Button) findViewById(R.id.farmerDashboard);
        btnUrdu = (Button) findViewById(R.id.Urdu);
        btnEnglish = (Button) findViewById(R.id.English);
        LanguageManager languageManager=new LanguageManager(this);
        //When user click on Farmer Dashboard
        farmerDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Farmer_Dashboard.class));
            }
        });
        //When user click on Urdu Button for Language Change
        btnUrdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languageManager.updateResourse("ur");
                recreate();
            }
        });
        //When user click on English Button for Language Change
        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languageManager.updateResourse("en");
                recreate();
            }
        });
        //When user click on comission Dashboard Button
        commissionDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

    }
}