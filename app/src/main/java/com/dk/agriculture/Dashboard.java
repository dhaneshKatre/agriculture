package com.dk.agriculture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        SQLiteHelper db = new SQLiteHelper(this);
        Toast.makeText(this, db.getAllValues().toString(), Toast.LENGTH_SHORT).show();
    }
}
