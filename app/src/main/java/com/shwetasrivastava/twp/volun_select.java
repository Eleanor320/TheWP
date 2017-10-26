package com.shwetasrivastava.twp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class volun_select extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Query myRef;
    DatabaseReference database;
    SharedPreferences report;
    final ArrayList<String> volunteers=new ArrayList<>();
    Spinner spin_5;
    SharedPreferences volun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volun_select);
        database = FirebaseDatabase.getInstance().getReference("Volunteers");
        spin_5=(Spinner) findViewById(R.id.spinner5);
        spin_5.setOnItemSelectedListener(this);
        volun= this.getSharedPreferences("com.shwetasrivastava.twp", Context.MODE_PRIVATE);

        report= this.getSharedPreferences("com.shwetasrivastava.twp", Context.MODE_PRIVATE);
        final String center=report.getString("center chosen","default").toString();

        System.out.println("yo working"+center);
        System.out.println((center.getClass().getName()));
        final String x =center;
        final Object a=x;
        System.out.println(a);


        database.orderByValue().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child:dataSnapshot.getChildren()) {
                    for (DataSnapshot child2 : child.getChildren()) {
                        // System.out.println(a);
                        //if(child2.child("center").getValue()=="Delhi_1"){
                        if(child2.getValue().equals("Delhi_1")){
                            String value = (String) child.child("name").getValue();
                            System.out.println("Values working" +value);
                            volunteers.add(value);
                        }
                    }
                }
                System.out.println(volunteers);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ArrayAdapter<String> data_6 = new ArrayAdapter<String>(volun_select.this, android.R.layout.simple_spinner_item, volunteers);
        data_6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_5.setAdapter(data_6);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        volun.edit().putString("volunteer selected", item).apply();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(parent.getContext(), "Select a volunteer!", Toast.LENGTH_LONG).show();
    }

    public void fill_report(View view){

        Intent intent=new Intent(getApplicationContext(),fill_report2.class);
        intent.putExtra("volunteer selected", volun.getString("volunteer selected","default").toString());
        startActivity(intent);
    }
}
