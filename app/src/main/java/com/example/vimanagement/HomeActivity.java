package com.example.vimanagement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;

public class HomeActivity extends AppCompatActivity {
    private ViewFlipper gifFlipper;
    private ImageView news_gif;
    private AppCompatButton btn_next;

    int[] gifResources = {
            R.drawable.car2,
            R.drawable.auto,
            R.drawable.truck
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gifFlipper = findViewById(R.id.gifFlipper);
        news_gif = findViewById(R.id.news_gif);
        btn_next = findViewById(R.id.btn_next);

        // Load multiple GIFs into flipper
        for (int res : gifResources) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(this).asGif().load(res).into(imageView);
            gifFlipper.addView(imageView);
        }

        gifFlipper.setAutoStart(true);
        gifFlipper.setFlipInterval(8000);
        gifFlipper.startFlipping();

        // Load news gif as usual
        Glide.with(this).asGif().load(R.drawable.news1).into(news_gif);

        btn_next.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(0, R.anim.fade_out);
        });
    }
}