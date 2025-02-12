package com.example.helpinghands_is;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class HospitalActivityMain extends AppCompatActivity {
    Button hospitalbutton,routebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_main);
        hospitalbutton = findViewById(R.id.hospital);
        hospitalbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),ShowHospitals.class);
                startActivity(intent);
            }
        });
        routebutton = findViewById(R.id.route);
        routebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),RouteActivity.class);
                startActivity(intent);
            }
        });
    }

}


