package com.administra.feriaCaballo.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.administra.feriaCaballo.R;

public class VenueDia2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue2);

        Button btnGoggleMaps = findViewById(R.id.google_maps2);
        Button btnWaze = findViewById(R.id.waze2);
        Button btnUber = findViewById(R.id.uber2);

        btnGoggleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/Aut%C3%B3dromo+Hermanos+Rodr%C3%ADguez/@19.4040417,-99.0895156,15z/data=!4m5!3m4!1s0x0:0x33ff6581b240a2dd!8m2!3d19.4040417!4d-99.0895156"));
                startActivity(browserIntent);
            }
        });

        btnWaze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.waze.com/ul?ll=19.4040467%2C-99.0917043&navigate=yes&zoom=17https://www.waze.com/ul?ll=19.4040467%2C-99.0917043&navigate=yes&zoom=17"));
                startActivity(browserIntent);
            }
        });
        btnUber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.uber.com/ul/"));
                startActivity(browserIntent);
            }
        });

    }

}
