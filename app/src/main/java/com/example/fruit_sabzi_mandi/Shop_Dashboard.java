package com.example.fruit_sabzi_mandi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.fruit_sabzi_mandi.adapters.ViewPagerAdapter;
import com.example.fruit_sabzi_mandi.models.UsersDataHolder;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Shop_Dashboard extends AppCompatActivity {
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static final String SHRED_PREF = "sharedPrefs";
    public static final String Save_Email = "email";
    public static final String Save_Contact = "contact";
    String shopName = "Fruit Sabzi Mandi";
    String email;
    String Contact = "034xxxxxxxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop__dashboard);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        SharedPreferences pref = this.getSharedPreferences(SHRED_PREF, this.MODE_PRIVATE);
        email = pref.getString(Save_Email, "email");
        getShopName();
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(shopName.toString());
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        //Tab Activity
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);
        viewPagerAdapter =new ViewPagerAdapter((getSupportFragmentManager()));
        //ViewPager Adapter
        viewPagerAdapter.AddFragment(new Home(),"");
        viewPagerAdapter.AddFragment(new Post(),"");
        viewPagerAdapter.AddFragment(new Profile(),"");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_home_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_post_add_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_account_circle_24);

    }

    private void getShopName() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.child(email.replace(".",",")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UsersDataHolder usersDataHolder = dataSnapshot.getValue(UsersDataHolder.class);
                shopName = usersDataHolder.getShopName().toString();
                Contact = usersDataHolder.getPhone().toString();
                SharedPreferences sharedPreferences = getSharedPreferences(SHRED_PREF,MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putString(Save_Contact,Contact);
                editor.apply();
                getSupportActionBar().setTitle(shopName.toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}