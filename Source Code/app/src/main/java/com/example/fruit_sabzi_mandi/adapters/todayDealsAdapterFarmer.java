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

public class todayDealsAdapterFarmer extends RecyclerView.Adapter<todayDealsAdapterFarmer.ProgramViewHolder> {

    private List<todayDealsModelClass> itemList;
    private  View itemview;
    private Context context;
    private TextView todayDealTitle,todayDealDate,todayDealLocation,todayDealPrice,todayDealContact,todayDealImage;
    private String course_name;

    public  todayDealsAdapterFarmer(List<todayDealsModelClass>itemList,Context context){
        this.itemList = itemList;
        this.context = context;
    }
    @Override
    public ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        context = parent.getContext();
        View view = inflater.inflate(R.layout.today_deals_layout_farmer,parent,false);
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
