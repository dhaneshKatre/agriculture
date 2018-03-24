package com.dk.agriculture;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RentActivity extends AppCompatActivity {
    private Spinner spinnerEquip;
    private String equipment;
    private EditText noOfDaysField;
    public String days;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);

        spinnerEquip = findViewById(R.id.spinnerEquipId);
        noOfDaysField = findViewById(R.id.noOfDays);

        ArrayAdapter<String> mAdapterEquipment = new ArrayAdapter<String>(RentActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.equipments));
        mAdapterEquipment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEquip.setAdapter(mAdapterEquipment);


        spinnerEquip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                equipment = adapterView.getItemAtPosition(i).toString();
                if(i > 0){
                    Toast.makeText(RentActivity.this, equipment, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        days = noOfDaysField.getText().toString();

    }
}
