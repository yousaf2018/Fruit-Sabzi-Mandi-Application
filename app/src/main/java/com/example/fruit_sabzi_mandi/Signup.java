package com.example.fruit_sabzi_mandi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class Signup extends AppCompatActivity {
    private static final String TAG = "Check";
    EditText emailAddress, password, conformPassword;
    TextView textSinup;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        emailAddress  = (EditText) findViewById(R.id.emailAddress);
        password = (EditText) findViewById(R.id.password);
        conformPassword = (EditText) findViewById(R.id.conformPassword);
        textSinup = (TextView) findViewById(R.id.textSinup);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        //When user clicks on SignUp Button
        btnSignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = emailAddress.getText().toString();
                String passwd = password.getText().toString();
                String conformPasswd = conformPassword.getText().toString();
                if(email.isEmpty() || passwd.isEmpty() || conformPasswd.isEmpty()) {
                    // If name or password is not entered
                    Toast.makeText(getApplicationContext(), "Both Name and Password are required", Toast.LENGTH_LONG).show();
                }
                else if(!passwd.equals(conformPasswd)){
                    Toast.makeText(getApplicationContext(), "Passwords are not match", Toast.LENGTH_LONG).show();
                    Log.i(TAG,"Invalid-->"+email+" --->"+passwd+"    "+conformPasswd);

                }
                else {
                    FirebaseAuth firebaseAuth;
                    firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.createUserWithEmailAndPassword(email,passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Account is successfully created",Toast.LENGTH_LONG).show();
                               // startActivity(new Intent(com.example.quizapplication.Signup.this, Courses_Dashboard.class));
                            }
                        }
                    });
                }
            }
        });
        //When user click on Login Text
        //If user click on Sinup text than redirect to Signup page
        textSinup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));

            }
        });
    }


}