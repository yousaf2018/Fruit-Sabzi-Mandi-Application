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
import com.example.fruit_sabzi_mandi.models.UsersDataHolder;
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

import javax.xml.namespace.QName;

public class comissionShopsContactsAdapter extends RecyclerView.Adapter<comissionShopsContactsAdapter.ProgramViewHolder> {

    private List<UsersDataHolder> itemList;
    private  View itemview;
    private Context context;
    private TextView title1,name1,contact1,location1,email1;


    public  comissionShopsContactsAdapter(List<UsersDataHolder>itemList,Context context){
        this.itemList = itemList;
        this.context = context;
    }
    @Override
    public ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        context = parent.getContext();
        View view = inflater.inflate(R.layout.commission_shops_contacts_layout,parent,false);
        return new ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramViewHolder holder, int position) {
        String name = itemList.get(position).getShopName();
        String location = itemList.get(position).getLocation();
        String contact = itemList.get(position).getPhone();
        String email = itemList.get(position).getEmail();
        holder.setData(name,location,contact,email,position,holder);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ProgramViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "Check";
        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            itemview = itemView;
            title1 = (TextView) itemView.findViewById(R.id.title);
            name1 = (TextView) itemView.findViewById(R.id.name);
            location1 = (TextView) itemView.findViewById(R.id.location);
            contact1 = (TextView) itemView.findViewById(R.id.contact);
            email1 = (TextView) itemView.findViewById(R.id.email);
        }

        public void setData(String name,String location,String contact,String email, int position, ProgramViewHolder holder) {
            title1.setText(location);
            name1.setText(name);
            location1.setText(location);
            contact1.setText(contact);
            email1.setText(email);
        }

    }
    public interface onCloseListener{
        void onCloseListener(int position);
    }
}
