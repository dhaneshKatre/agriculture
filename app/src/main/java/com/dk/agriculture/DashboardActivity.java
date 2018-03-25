package com.dk.agriculture;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    LocationManager locationManager;
    LocationListener locationListener;
    Double lat, lang;
    private Button placeAd, myAd,rent,soil, cropman;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)

                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("Agriculture");

        final SQLiteHelper db = new SQLiteHelper(this);
        Toast.makeText(this, db.getAllValues().toString(), Toast.LENGTH_SHORT).show();
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        placeAd = findViewById(R.id.placeAd);
        myAd = findViewById(R.id.myAd);
        rent = findViewById(R.id.rent);
        soil = findViewById(R.id.soil);
        cropman= findViewById(R.id.cropman);
        cropman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this,FarmScheduleActivity.class));
            }
        });
        locationListener = new LocationListener(){
            @Override
            public void onLocationChanged(Location location) {

                Log.i("Location", location.toString());
                lat = location.getLatitude();
                lang = location.getLongitude();
                //Toast.makeText(DashboardActivity.this,location.toString() , Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (Build.VERSION.SDK_INT < 23) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

            }
        } else

        {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                // ask for permission

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


            } else {

                // we have permission!

                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

                }


            }

        }

        placeAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, AdEquipmentActivity.class);
                intent.putExtra("lat", lat);
                intent.putExtra("lang", lang);
                startActivity(intent);
            }
        });

        myAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, MyAdsActivity.class));
            }
        });

        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rentIntent = new Intent(DashboardActivity.this,RentActivity.class);
                rentIntent.putExtra("lat", lat);
                rentIntent.putExtra("lon", lang);
                startActivity(rentIntent);
            }
        });

        soil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent soilIntent = new Intent(DashboardActivity.this,TabActivity.class);
                soilIntent.putExtra("lat", lat);
                soilIntent.putExtra("lang", lang);
                startActivity(soilIntent);
            }
        });
    }

}
