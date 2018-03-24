package com.dk.agriculture;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdsActivity extends AppCompatActivity {
    private RecyclerView mMyAds;
    private DatabaseReference mDatabase;
    ArrayList<AddModel> addModels = new ArrayList<>();
   // private SQLiteHelper db = new SQLiteHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ads);
        mMyAds = (RecyclerView) findViewById(R.id.myAdsList);
        mMyAds.setHasFixedSize(true);
        mMyAds.setLayoutManager(new LinearLayoutManager(this));
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<AddModel,AdsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<AddModel,AdsViewHolder>(
                AddModel.class,
                R.layout.row,
                AdsViewHolder.class,
                mDatabase.child("farmer").child("9769084086").child("myAds")
        ) {

            @Override
            protected void populateViewHolder(AdsViewHolder viewHolder, AddModel model, final int position) {
                addModels.add(model);
                viewHolder.setEquipment(model.getEquipment());
                viewHolder.setEquipmentDesc(model.getEquipmentdesc());
                viewHolder.setMaxDays(model.getMaxdays());
                viewHolder.setPricePerDay(model.getPriceperday());
                viewHolder.mEditButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(MyAdsActivity.this, addModels.get(position).getEquipment(), Toast.LENGTH_SHORT).show();
                        confromation(addModels.get(position));
                    }
                });
                viewHolder.mCancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FirebaseDatabase.getInstance().getReference("market").child(addModels.get(position).getEquipment()).child(addModels.get(position).getId()).removeValue();
                        FirebaseDatabase.getInstance().getReference("farmer").child("9769084086").child("myAds").child(addModels.get(position).getId()).removeValue();
                    }
                });
            }
        };
        mMyAds.setAdapter(firebaseRecyclerAdapter);
    }

    public static class AdsViewHolder extends RecyclerView.ViewHolder{
        View mView;
        Button mEditButton,mCancelBtn;
        public AdsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mEditButton =mView.findViewById(R.id.editBtn);
            mCancelBtn= mView.findViewById(R.id.cancelBtn);
        }
        public void setEquipment(String Equipment){
            TextView equipment = (TextView) mView.findViewById(R.id.equipmentVal);
            equipment.setText(Equipment);
        }
        public void setEquipmentDesc(String EquipmentDesc){
            TextView equipmentDesc = (TextView) mView.findViewById(R.id.equipmentdescVal);
            equipmentDesc.setText(EquipmentDesc);
        }
        public void setMaxDays(String MaxDays){
            TextView maxDays = (TextView) mView.findViewById(R.id.maxdaysVal);
            maxDays.setText(MaxDays);
        }
        public void setPricePerDay(String PricePerDay){
            TextView pricePerDay = (TextView) mView.findViewById(R.id.pricePerDayVal);
            pricePerDay.setText(PricePerDay);
        }
    }

    public void confromation(AddModel addModel)
    {
        final AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        final View conformationview = layoutInflater.inflate(R.layout.conformationdialog, null);
        dialogbuilder.setView(conformationview);
        dialogbuilder.setTitle("EDIT AD");
        dialogbuilder.setMessage("Are You Sure?");
        Button confirm = (Button) conformationview.findViewById(R.id.confirm);
        EditText maxdays = conformationview.findViewById(R.id.maxdays);
        EditText priceperday = conformationview.findViewById(R.id.priceperday);
        Spinner equipments = conformationview.findViewById(R.id.equipments);
        ArrayAdapter<String> mAdapterComponent= new ArrayAdapter<String>(MyAdsActivity.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.equipments));
        mAdapterComponent.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        equipments.setAdapter(mAdapterComponent);
        equipments.setSelection(1);

        maxdays.setText(addModel.getMaxdays());
        priceperday.setText(addModel.getPriceperday());
        Button cancel = (Button) conformationview.findViewById(R.id.cancel);
        final AlertDialog conformationdialog = dialogbuilder.create();
        conformationdialog.show();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conformationdialog.dismiss();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}