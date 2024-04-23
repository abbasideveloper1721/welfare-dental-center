package com.example.server;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText edname, edemail, edpass;
    String sname,semail,spass;
    String url = "https://auctioned.000webhostapp.com/insertdbform.php";
    Button bt, regbt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edname = findViewById(R.id.name);
        edemail = findViewById(R.id.email);
        edpass = findViewById(R.id.password);
        bt = findViewById(R.id.signup);
        regbt = findViewById(R.id.loginbutton);

        regbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sname = edname.getText().toString().trim();
                semail = edemail.getText().toString().trim();
                spass = edpass.getText().toString().trim();

                if (!sname.isEmpty() && !semail.isEmpty() && !spass.isEmpty()){




                StringRequest rq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> para = new HashMap<String,String>();
                        para.put("name", sname);
                        para.put("email", semail);
                        para.put("pass",spass);
                        return para;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(rq);

                }else {
                    Toast.makeText(MainActivity.this, "Please fill all feilds", Toast.LENGTH_SHORT).show();
                    edname.setText("");
                    edpass.setText("");
                    edemail.setText("");
                }


            }
        });

    }
}