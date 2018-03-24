package com.dk.agriculture;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SoilActivity extends AppCompatActivity {

    private double currentLat,currentLon;
    private String type ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soil);

        Intent intent = getIntent();
        currentLat = intent.getDoubleExtra("lat", 0.0);
        currentLon = intent.getDoubleExtra("lang", 0.0);
        checkSoilType(currentLat,currentLon);

    }

    private void checkSoilType(double lat, double lon) {

        //Forest & Mountains (36.53,73.57) (35.03,80.19) (32.33,75.321) (32.083,74.78)
        //Alluvial    (31.88,75.15) (25.96,87.54) (29.53,73.677) (28.9,74.13)
        // Red and Yellow (24.88,81.11) (25.22,87.84) (17.18,83.62) (21.08,79.92)
        //Black (23.56,74.42) (24.18,82.836) (18.89,79.887) (18.15,73.11)
        //Arid (29.06,72.57) (28.514,74.987) (24.79,71.09) (26.86,69.75)

        if ((lat >= 36.53 && lat <= 35.03) && (lat >=32.083 && lat <= 32.33) && (lon <= 73.57 && lon <= 80.19) && (lon >= 74.78&& lon >= 75.321)){
            type = "Forest & Mountains";

        }else if ((lat >= 31.88 && lat <= 25.96) && (lat >=28.9 && lat <= 29.53) && (lon <= 75.15 && lon <= 87.54) && (lon >= 74.13&& lon >= 73.677)){
            type = "Alluvial ";

        }else if ((lat >= 24.88 && lat <= 25.22) && (lat >=21.08 && lat <= 17.18) && (lon <= 81.11 && lon <= 87.84) && (lon >= 79.92&& lon >=83.62)){
            type = "Red and Yellow ";

        }else if ((lat >= 23.56 && lat <= 24.18) && (lat >=18.15 && lat <= 18.89) && (lon <= 74.42 && lon <= 82.836) && (lon >= 73.11 && lon >= 79.887)){
            type = "Black";

        }else if ((lat >= 29.06 && lat <= 28.514) && (lat >=26.86 && lat <= 24.79) && (lon <= 72.57 && lon <= 74.987) && (lon >= 69.75 && lon >= 71.09)){
            type = "Arid";

        }else{
            type = "Red and Yellow ";

        }

        switch (type){
            case "Forest & Mountains" :
                break;
            case "Alluvial " :
                break;
            case "Red and Yellow " :
                break;
            case "Black" :
                break;
            case "Arid" :
                break;

        }

        Toast.makeText(this, type, Toast.LENGTH_SHORT).show();
    }
}
