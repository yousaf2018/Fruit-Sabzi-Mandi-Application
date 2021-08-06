package com.example.fruit_sabzi_mandi.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fruit_sabzi_mandi.R;
import com.example.fruit_sabzi_mandi.models.todayDealsModelClass;

import org.w3c.dom.Text;

import java.util.List;

public class todayDealsAdapter extends RecyclerView.Adapter<todayDealsAdapter.ProgramViewHolder> {

    private List<todayDealsModelClass> itemList;
    private  View itemview;
    private Context context;
    private  int todayDealImage;
    private TextView todayDealTitle,todayDealDate,todayDealLocation,todayDealPrice,todayDealContact;
    private String course_name;

    public  todayDealsAdapter(List<todayDealsModelClass>itemList){
        this.itemList = itemList;
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
        int todayDealImage1 = itemList.get(position).getTodayDealImage();
        String todayDealTitle = itemList.get(position).getTodayDealTitle();
        String todayDealPrice = itemList.get(position).getTodayDealPrice();
        String todayDealLocation = itemList.get(position).getTodayDealLocation();
        String todayDealDate = itemList.get(position).getTodayDealDate();
        String todayDealContact = itemList.get(position).getTodayDealContact();
        holder.setData(todayDealImage1,todayDealTitle,todayDealDate,todayDealLocation,todayDealPrice,todayDealContact);
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

        public void setData(int todayDealImage1,String todayDealTitle1,String todayDealDate1,String todayDealLocation1,String todayDealPrice1,String todayDealContact1) {
            todayDealImage.setImageResource(todayDealImage1);
            todayDealTitle.setText(todayDealTitle1);
            todayDealDate.setText(todayDealDate1);
            todayDealLocation.setText(todayDealLocation1);
            todayDealPrice.setText(todayDealPrice1);
            todayDealContact.setText(todayDealContact1);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(context, QuestionActivity.class);
                   // intent.putExtra("course_name", course_name);
                  //  Log.d("Hiiii",course_name);
                    //context.startActivity(intent);
                }
            });
        }
    }
}
