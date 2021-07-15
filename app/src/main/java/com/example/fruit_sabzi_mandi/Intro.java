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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        commissionDashboard = (Button) findViewById(R.id.commissionDashboard);
        farmerDashboard = (Button) findViewById(R.id.farmerDashboard);
        btnUrdu = (Button) findViewById(R.id.Urdu);
        btnEnglish = (Button) findViewById(R.id.English);
        LanguageManager languageManager=new LanguageManager(this);
        commissionDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(com.example.quizapplication.Intro.this,.class));
            }
        });
        btnUrdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languageManager.updateResourse("ur");
                recreate();
            }
        });
        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languageManager.updateResourse("en");
                recreate();
            }
        });
        commissionDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

    }
}