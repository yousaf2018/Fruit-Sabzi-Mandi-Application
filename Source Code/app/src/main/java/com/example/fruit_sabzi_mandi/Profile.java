package com.example.fruit_sabzi_mandi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fruit_sabzi_mandi.models.UsersDataHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class Profile extends Fragment {
    View view;
    public static final String SHRED_PREF = "sharedPrefs";
    public static final String Save_Email = "email";
    EditText name,location,contact;
    Button btnLogout;
    String email,name1,location1,contact1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        name = view.findViewById(R.id.name);
        location = view.findViewById(R.id.location);
        contact = view.findViewById(R.id.contact);
        btnLogout = view.findViewById(R.id.btnLogout);
        SharedPreferences pref = getActivity().getSharedPreferences(SHRED_PREF, getActivity().MODE_PRIVATE);
        email = pref.getString(Save_Email, "email");
        getShopDetails();
        name.setHint(name1);
        location.setHint(location1);
        contact.setHint(contact1);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getActivity(),"Logout Successfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(), Intro.class));
            }
        });
        return view;
    }

    private void getShopDetails() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.child(email.replace(".",",")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UsersDataHolder usersDataHolder = dataSnapshot.getValue(UsersDataHolder.class);
                name1 = usersDataHolder.getShopName().toString();
                contact1 = usersDataHolder.getPhone().toString();
                location1 = usersDataHolder.getLocation().toString();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHRED_PREF,getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}