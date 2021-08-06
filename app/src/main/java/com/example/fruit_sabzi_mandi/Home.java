package com.example.fruit_sabzi_mandi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fruit_sabzi_mandi.adapters.todayDealsAdapter;
import com.example.fruit_sabzi_mandi.models.todayDealsModelClass;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initData();
        recyclerView1 = view.findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView1.setLayoutManager(gridLayoutManager);
        adapter = new todayDealsAdapter(itemList);
        recyclerView1.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
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
        itemList =new ArrayList<>();
        itemList.add(new todayDealsModelClass("Mangoes",R.drawable.apples,
                "14/03/2021","Layyah", "180 RS/Kg","03346966320"));
        itemList.add(new todayDealsModelClass("Oranges",R.drawable.fruit_sabzi_image,
                "14/03/2021","Layyah", "170 RS/Kg","03346966320"));
        itemList.add(new todayDealsModelClass("Alo",R.drawable.fruit_sabzi_image,
                "14/03/2021","Layyah", "100 RS/Kg","03346966320"));
        itemList.add(new todayDealsModelClass("Tomatoes",R.drawable.fruit_sabzi_image,
                "14/03/2021","Layyah", "110 RS/Kg","03346966320"));
    }

}