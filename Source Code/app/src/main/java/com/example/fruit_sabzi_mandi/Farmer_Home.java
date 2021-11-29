package com.example.fruit_sabzi_mandi;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fruit_sabzi_mandi.adapters.todayDealsAdapterFarmer;
import com.example.fruit_sabzi_mandi.models.UsersDataHolder;
import com.example.fruit_sabzi_mandi.models.todayDealsModelClass;
import com.example.fruit_sabzi_mandi.adapters.todayDealsAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Farmer_Home extends Fragment {
    View view;
    RecyclerView recyclerView1;
    LinearLayoutManager linearLayoutManager;
    List<todayDealsModelClass> itemList;
    RecyclerView.Adapter adapter;
    ArrayList<String > phoneNumbers=new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_farmer__home, container, false);
        initData();
        getDataFromFirebase();
        recyclerView1 = view.findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView1.setLayoutManager(gridLayoutManager);
        adapter = new todayDealsAdapterFarmer(itemList,getActivity());
        recyclerView1.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    private void getDataFromFirebase() {
        //Login admin user for farmer dashboard
        FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword("farmer@gmail.com", "123456").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                } else {
                }
            }
        });
        //Getting all shops contacts
        Query query = FirebaseDatabase.getInstance().getReference("Users");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    UsersDataHolder data = childSnapshot.getValue(UsersDataHolder.class);
                    if(data.getPhone().toString()!=null){
                        phoneNumbers.add(data.getPhone().toString());
                    }
                }
                //Getting details of all shops deals
                if(phoneNumbers.size()!=0){
                    itemList.clear();
                }
                for(int i=0;i<phoneNumbers.size();i++){
                    // Read from the database
                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Deals_Record").child(phoneNumbers.get(i));
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            for (DataSnapshot ds : dataSnapshot.getChildren()){
                                todayDealsModelClass data = (todayDealsModelClass) ds.getValue(todayDealsModelClass.class);
                                 itemList.add(data);
                                 adapter.notifyDataSetChanged();
                            }
                        }


                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                        }
                    });
                    if(i+1>phoneNumbers.size()){
                        adapter.notifyDataSetChanged();
                    }

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void initData() {
        String urlImage = "https://www.sampabjj.com/wp-content/uploads/2017/04/default-image.jpg";
        itemList =new ArrayList<>();
        itemList.add(new todayDealsModelClass(urlImage,"Loading data wait a while if available",
                "0 Per/Kg","Unkown", "00/00/0000","03xxxxxxxx"));
    }

}