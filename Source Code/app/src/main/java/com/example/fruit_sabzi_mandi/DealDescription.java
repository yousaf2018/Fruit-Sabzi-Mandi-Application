package com.example.fruit_sabzi_mandi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.fruit_sabzi_mandi.adapters.DealDescriptionAdapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class DealDescription extends AppCompatActivity {
    SliderView sliderView;
    int[] images = {R.drawable.namal};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_description);
        sliderView = findViewById(R.id.imageSlider);
        DealDescriptionAdapter dealDescriptionAdapter = new DealDescriptionAdapter(images);
        sliderView.setSliderAdapter(dealDescriptionAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderView.startAutoCycle();
    }
}