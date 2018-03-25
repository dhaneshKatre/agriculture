package com.dk.agriculture;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Locale;

import static com.dk.agriculture.R.menu.menu_main;
import static com.dk.agriculture.R.id.english;
import static com.dk.agriculture.R.id.hindi;


public class multilingual extends AppCompatActivity {
    TextView mPlaceOrder,mMyAds,mRent,mSoil;
    private Locale myLocale;
    private static final String Locale_Preference = "Locale Preference";
    private static final String Locale_KeyValue = "Saved Locale";
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multilingual);
        mPlaceOrder = findViewById(R.id.placeOrder);
        mMyAds = findViewById(R.id.myAds);
        mRent = findViewById(R.id.rent);
        mSoil = findViewById(R.id.soil);
        sharedPreferences = getSharedPreferences(Locale_Preference, Activity.MODE_PRIVATE);
        editor =sharedPreferences.edit();
        loadLocale();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == english){
            String lang = "en";
            changeLocale(lang);
        }
        if(item.getItemId() == hindi){
            String lang = "hi";
            changeLocale(lang);
        }

        return super.onOptionsItemSelected(item);
    }

    private void changeLocale(String lang) {
        myLocale = new Locale(lang);//Set Selected Locale
        saveLocale(lang);//Save the selected locale
        Locale.setDefault(myLocale);//set new locale as default
        Configuration config = new Configuration();//get Configuration
        config.locale = myLocale;//set config locale as selected locale
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());//Update the config
        updateTexts();//Update texts according to locale
    }

    private void updateTexts() {
        mPlaceOrder.setText(R.string.place_an_ad);
        mMyAds.setText(R.string.my_ads);
        mSoil.setText(R.string.soil_analysis);
        mRent.setText(R.string.rent_an_equipment);
    }

    private void saveLocale(String lang) {
        editor.putString(Locale_KeyValue, lang);
        editor.commit();
    }
    public void loadLocale() {
        String language = sharedPreferences.getString(Locale_KeyValue, "");
        changeLocale(language);
    }
}
