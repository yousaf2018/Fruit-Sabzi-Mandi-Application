package com.example.fruit_sabzi_mandi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.fruit_sabzi_mandi.adapters.ViewPagerAdapter;
import com.example.fruit_sabzi_mandi.adapters.todayDealsAdapter;
import com.example.fruit_sabzi_mandi.models.UsersDataHolder;
import com.example.fruit_sabzi_mandi.models.todayDealsModelClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Farmer_Dashboard extends AppCompatActivity {
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ArrayList<String > phoneNumbers=new ArrayList<String>();
    String shopName = "Fruit Sabzi Mandi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer__dashboard);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(shopName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        //Tab Activity
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);
        viewPagerAdapter =new ViewPagerAdapter((getSupportFragmentManager()));
        //ViewPager Adapter
        viewPagerAdapter.AddFragment(new Farmer_Home(),"");
        viewPagerAdapter.AddFragment(new Commission_Shops_Contacts(),"");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_home_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_account_circle_24);
    }

}