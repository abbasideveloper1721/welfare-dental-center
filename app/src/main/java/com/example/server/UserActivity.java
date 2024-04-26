package com.example.server;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

public class UserActivity extends AppCompatActivity {

    TextView nametextview,IDtextview,emailtextview,timetextbox;
    ;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        nametextview = findViewById(R.id.textViewName);
        timetextbox = findViewById(R.id.timefeild);

        Date datecurrent = Calendar.getInstance().getTime();
        timetextbox.setText(datecurrent.toString());

        String xmlrep = getIntent().getStringExtra("xmlresponse");


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

                nametextview.setText("Welcome " + name);

                ;
            } else {
                // Handle case where success is false
                Toast.makeText(UserActivity.this, "Error: Failed to fetch user data.", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();

            Toast.makeText(UserActivity.this, "Error: Failed to parse JSON.", Toast.LENGTH_SHORT).show();
        }
    }



        };

