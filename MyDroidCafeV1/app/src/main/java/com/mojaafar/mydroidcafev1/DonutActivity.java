package com.mojaafar.mydroidcafev1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DonutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        TextView donutTitle = findViewById(R.id.donut_title);
        donutTitle.setText(getIntent().getStringExtra("dTitle"));
        TextView donutDescription= findViewById(R.id.donut_description);
        donutDescription.setText(getIntent().getStringExtra("dDescription"));
        ImageView donutImage = findViewById(R.id.donut_image);
        Glide.with(this).load(getIntent().getIntExtra("dImage", 0)).into(donutImage);
    }
}
