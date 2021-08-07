package com.example.fruit_sabzi_mandi;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

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
import com.example.fruit_sabzi_mandi.models.todayDealsModelClass;
import com.example.fruit_sabzi_mandi.adapters.todayDealsAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {
    View view;
    RecyclerView recyclerView1;
    LinearLayoutManager linearLayoutManager;
    List<todayDealsModelClass> itemList;
    RecyclerView.Adapter adapter;
    TextView todayDealTitle,todayDealPrice,todayDealLocation,todayDealDate;
    ImageView todayDealImage;
    public static final String SHRED_PREF = "sharedPrefs";
    public static final String Save_Email = "email";
    public static final String Save_Contact = "contact";
    String Contact = "034xxxxxxx";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        SharedPreferences pref = getActivity().getSharedPreferences(SHRED_PREF, getActivity().MODE_PRIVATE);
        Contact = pref.getString(Save_Contact, "contact");
        Toast.makeText(getActivity(),""+Contact,Toast.LENGTH_LONG).show();
        initData();
        getDataFromFirebase();
        recyclerView1 = view.findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView1.setLayoutManager(gridLayoutManager);
        adapter = new todayDealsAdapter(itemList);
        recyclerView1.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    private void getDataFromFirebase() {
        // Read from the database
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Deals_Record").child(Contact);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                itemList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    todayDealsModelClass data = (todayDealsModelClass) ds.getValue(todayDealsModelClass.class);
                    itemList.add(data);
                    adapter.notifyDataSetChanged();
                    Log.d("Hi", "Firebase Records showing   :>>>"+data.getTodayDealTitle());
                }
            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }


    /*private void setUpFirsStore() {
            firestore = FirebaseFirestore.getInstance();
            Map<String, Object> data1 = new HashMap<>();
            firestore.collection(quizTitle).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot data:list){
                                quiz object = new quiz();
                                Log.d(TAG, "onSuccess: "+data.toString());
                                quiz_list.add(data.toObject(quiz.class));
                                itemList.clear();
                                itemList.add(new Model_Class(R.drawable.ic_icons8_quizlet,quiz_list.get(0).getTitle()));
                                adapter.notifyDataSetChanged();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: ");
                        }
                    });
        }
    */
    private void initData() {
        String urlImage = "https://www.sampabjj.com/wp-content/uploads/2017/04/default-image.jpg";
        itemList =new ArrayList<>();
        itemList.add(new todayDealsModelClass(urlImage,"Loading data wait a while",
                "0 Per/Kg","Unkown", "00/00/0000","03xxxxxxxx"));
    }

}