package com.example.server;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

public class UserActivity extends AppCompatActivity {

    TextView responsetxt,nametextview,IDtextview,emailtextview,tvUserId;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        IDtextview = findViewById(R.id.textViewId);
        nametextview = findViewById(R.id.textViewName);
        emailtextview = findViewById(R.id.textViewEmail);
        tvUserId = findViewById(R.id.iduser);

        responsetxt = findViewById(R.id.xmltext);
        String xmlrep = getIntent().getStringExtra("xmlresponse");
        responsetxt.setText(xmlrep);

        try {
            // Parse the JSON string
            JSONObject jsonObject = new JSONObject(xmlrep);

            // Check if the operation was successful
            if (jsonObject.getBoolean("success")) {
                // Extract the nested JSON object for user info
                JSONObject userInfo = jsonObject.getJSONObject("user_info");

                // Extract data from the user_info object
                String id = String.valueOf(userInfo.getInt("ID"));
                String name = userInfo.getString("name");
                String email = userInfo.getString("email");

                // Set the extracted data to TextViews
                IDtextview.setText("ID: " + id);
                nametextview.setText("Name: " + name);
                emailtextview.setText("Email: " + email);
            } else {
                // Handle case where success is false
                tvUserId.setText("Error: Failed to fetch user data.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            tvUserId.setText("Error: Failed to parse JSON.");
        }
    }



        };

