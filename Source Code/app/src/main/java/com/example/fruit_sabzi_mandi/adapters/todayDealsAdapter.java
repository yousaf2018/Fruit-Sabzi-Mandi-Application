package com.example.fruit_sabzi_mandi.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.Model;
import com.example.fruit_sabzi_mandi.R;
import com.example.fruit_sabzi_mandi.models.todayDealsModelClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class todayDealsAdapter extends RecyclerView.Adapter<todayDealsAdapter.ProgramViewHolder> {

    private List<todayDealsModelClass> itemList;
    private  View itemview;
    private Context context;
    private TextView todayDealTitle,todayDealDate,todayDealLocation,todayDealPrice,todayDealContact,todayDealImage;
    private String course_name;

    public  todayDealsAdapter(List<todayDealsModelClass>itemList,Context context){
        this.itemList = itemList;
        this.context = context;
    }
    @Override
    public ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        context = parent.getContext();
        View view = inflater.inflate(R.layout.today_deals_layout,parent,false);
        return new ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramViewHolder holder, int position) {
        final todayDealsModelClass temp=itemList.get(position);
        String todayDealImage1 = itemList.get(position).getImageUrl();
        String todayDealTitle = itemList.get(position).getTodayDealTitle();
        String todayDealPrice = itemList.get(position).getTodayDealPrice();
        String todayDealLocation = itemList.get(position).getTodayDealLocation();
        String todayDealDate = itemList.get(position).getTodayDealDate();
        String todayDealContact = itemList.get(position).getTodayDealContact();
        holder.setData(todayDealImage1,todayDealTitle,
                todayDealDate,todayDealLocation,
                todayDealPrice,todayDealContact,position,holder);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ProgramViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "Check";
        ImageView todayDealImage;
        TextView todayDealTitle,todayDealPrice,todayDealLocation,todayDealDate;
        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            itemview = itemView;
            todayDealImage = itemView.findViewById(R.id.todayDealImage);
            todayDealTitle = (TextView) itemView.findViewById(R.id.todayDealTitle);
            todayDealPrice = (TextView) itemView.findViewById(R.id.todayDealPrice);
            todayDealLocation = (TextView) itemView.findViewById(R.id.todayDealLocation);
            todayDealDate = (TextView) itemView.findViewById(R.id.todayDealDate);
            todayDealContact = (TextView) itemView.findViewById(R.id.todayDealContactNumber);
        }

        public void setData(String todayDealImage1, String todayDealTitle1,
                            String todayDealDate1, String todayDealLocation1,
                            String todayDealPrice1, String todayDealContact1,int position,ProgramViewHolder holder) {
            holder.itemView.findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String contact,Title;
                    contact = itemList.get(position).getTodayDealContact();
                    Title = itemList.get(position).getTodayDealTitle();
                    deleteDeal(contact,Title);
                    itemList.remove(position);
                    notifyDataSetChanged();
                }

                private void deleteDeal(String contact,String Title) {
                    // Read from the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Deals_Record").child(contact);
                    Query query = myRef.orderByChild("todayDealTitle").equalTo(Title);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                appleSnapshot.getRef().removeValue();
                                Toast.makeText(context,"Deal is successfully deleted",Toast.LENGTH_LONG).show();
                                if (itemList.size()==0){
                                    Toast.makeText(context,"There no deal available",Toast.LENGTH_LONG).show();
                                    String urlImage = "https://www.sampabjj.com/wp-content/uploads/2017/04/default-image.jpg";
                                    itemList =new ArrayList<>();
                                    itemList.add(new todayDealsModelClass(urlImage,"No deal available Kindly upload",
                                            "0 Per/Kg","Unkown", "00/00/0000","03xxxxxxxx"));
                                    notifyDataSetChanged();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d("Hi", "onCancelled", databaseError.toException());
                        }
                    });

                }
            });
            Glide.with(context)
                    .load(todayDealImage1)
                    .into(todayDealImage);
            todayDealTitle.setText(todayDealTitle1);
            todayDealDate.setText(todayDealDate1);
            todayDealLocation.setText(todayDealLocation1);
            todayDealPrice.setText(todayDealPrice1);
            todayDealContact.setText(todayDealContact1);
        }

    }
    public interface onCloseListener{
        void onCloseListener(int position);
    }
}
