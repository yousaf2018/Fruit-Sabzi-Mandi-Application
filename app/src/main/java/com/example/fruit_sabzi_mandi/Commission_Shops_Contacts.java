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
import com.example.fruit_sabzi_mandi.adapters.comissionShopsContactsAdapter;
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

public class Commission_Shops_Contacts extends Fragment {
    View view;
    RecyclerView recyclerView1;
    LinearLayoutManager linearLayoutManager;
    List<UsersDataHolder> itemList;
    RecyclerView.Adapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_commission__shops__contacts, container, false);
        initData();
        getDataFromFirebase();
        recyclerView1 = view.findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView1.setLayoutManager(gridLayoutManager);
        adapter = new comissionShopsContactsAdapter(itemList,getActivity());
        recyclerView1.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    private void getDataFromFirebase() {
        //Getting all shops details
        Query query = FirebaseDatabase.getInstance().getReference("Users");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemList.clear();
                for(DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    UsersDataHolder data = childSnapshot.getValue(UsersDataHolder.class);
                    if(data.getPhone()!=null){
                        itemList.add(data);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void initData() {
        itemList =new ArrayList<>();
        itemList.add(new UsersDataHolder("Location", "034xxxxxxxx", "Shop Name","abc@gmail.com"));
    }

}