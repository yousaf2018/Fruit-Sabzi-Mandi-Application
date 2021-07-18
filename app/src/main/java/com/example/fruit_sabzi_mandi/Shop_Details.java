package com.example.fruit_sabzi_mandi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Shop_Details extends AppCompatActivity {
    private static final String TAG = "Check";
    EditText Phone,Location,ShopName;
    TextView textSinup;
    Button btnSignup;
    Button btnEnglish;
    Button btnUrdu;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop__details);
        Phone  = (EditText) findViewById(R.id.phone);
        Location = (EditText) findViewById(R.id.location);
        ShopName = (EditText) findViewById(R.id.shopName);
        textSinup = (TextView) findViewById(R.id.textSinup);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        btnEnglish = (Button) findViewById(R.id.English);
        btnUrdu = (Button) findViewById(R.id.Urdu);
        LanguageManager languageManager=new LanguageManager(this);
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
        //When user clicks on SignUp Button
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String locationShop = Location.getText().toString();
                String PhoneShop = Phone.getText().toString();
                String NameShop = ShopName.getText().toString();
                if(locationShop.isEmpty() || PhoneShop.isEmpty() || NameShop.isEmpty()) {
                    Log.d("Hi","Location-->"+Location.getText().toString()+" Phone--->"+Phone.getText().toString()+" ShopName--->"+ShopName.getText().toString());
                    // If name or password is not entered
                    Toast.makeText(getApplicationContext(), "Please make sure all fields are filled "+locationShop+" "+PhoneShop+" "+NameShop, Toast.LENGTH_LONG).show();
                }
                else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Users");
                    UsersDataHolder usersDataHolder=new UsersDataHolder(locationShop,PhoneShop,NameShop);
                    myRef.child(PhoneShop).setValue(usersDataHolder);
                    Toast.makeText(getApplicationContext(), "Congrats your account creation is successfull", Toast.LENGTH_LONG).show();
                }
            }
        });
        //When user click on Login Text
        textSinup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));

            }
        });
    }
}