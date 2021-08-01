package com.example.fruit_sabzi_mandi.adapters;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.fruit_sabzi_mandi.DealDescription;
import com.example.fruit_sabzi_mandi.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class DealDescriptionAdapter extends SliderViewAdapter<DealDescriptionAdapter.Holder> {
    int[] images;
    public DealDescriptionAdapter(int[] images){
        this.images = images;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slider_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
        viewHolder.imageView.setImageResource(images[position]);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    public class Holder extends SliderViewAdapter.ViewHolder{
                ImageView imageView;
                public Holder(View itemView){
                    super(itemView);
                    imageView = itemView.findViewById(R.id.imageView);


                }
        }
}
