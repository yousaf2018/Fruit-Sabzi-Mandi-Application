package com.example.fruit_sabzi_mandi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private static final String TAG = "Check";
    EditText emailAddress, password;
    TextView textSinup;
    Button btnLogin;
    Button btnUrdu;
    Button btnEnglish;
    public static final String SHRED_PREF = "sharedPrefs";
    public static final String Save_Email = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailAddress = (EditText) findViewById(R.id.emailAddress);
        password = (EditText) findViewById(R.id.password);
        textSinup = (TextView) findViewById(R.id.textSinup);
        btnLogin = (Button) findViewById(R.id.btnLogin);
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
        //When user click on Login button
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = emailAddress.getText().toString();
                String passwd = password.getText().toString();
                if (email.isEmpty() || passwd.isEmpty()) {
                    // If name or password is not entered
                    Toast.makeText(getApplicationContext(), "Both Email and Password are required", Toast.LENGTH_LONG).show();
                } else {
                    FirebaseAuth firebaseAuth;
                    firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.signInWithEmailAndPassword(email, passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                                SharedPreferences sharedPreferences = getSharedPreferences(SHRED_PREF,MODE_PRIVATE);
                                SharedPreferences.Editor editor= sharedPreferences.edit();
                                Toast.makeText(getApplicationContext(),""+emailAddress.getText().toString(),Toast.LENGTH_LONG).show();
                                editor.putString(Save_Email,emailAddress.getText().toString());
                                editor.apply();
                                startActivity(new Intent(getApplicationContext(), Shop_Dashboard.class));
                            } else {
                                Toast.makeText(getApplicationContext(), "Login not Successfully", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }
            }
        });
        //If user click on Sinup text than redirect to Signup page
        textSinup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Signup.class));

            }
        });


    }

}