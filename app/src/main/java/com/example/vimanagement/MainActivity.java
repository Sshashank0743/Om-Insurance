package com.example.vimanagement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //GIF file
        ImageView gifImageView = findViewById(R.id.gifImageView);
        Glide.with(this).asGif().load(R.drawable.car).into(gifImageView);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);

        //isLoggedin
//        boolean isLoggedIn = false;
//        if (isLoggedIn) {
//            Intent intent = new Intent(MainActivity.this, Customer_Details_Activity.class);
//            startActivity(intent);
//            finish();
//        }
        //Button
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> {
            String userEmail = email.getText().toString();
            String userPass = password.getText().toString();

            if (userEmail.equals("shashank@gmail.com") && userPass.equals("shank@2026")) {
                Intent intent = new Intent(MainActivity.this, Customer_Details_Activity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
