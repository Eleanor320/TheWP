package com.shwetasrivastava.twp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class mark_attendance extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner2;
    FirebaseDatabase database;
    DatabaseReference myRef;
    SharedPreferences report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Students");
        report= this.getSharedPreferences("com.shwetasrivastava.twp", Context.MODE_PRIVATE);
        spinner2=(Spinner)findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);
        ArrayList<String> centers = new ArrayList<String>();
        centers.add("");
        centers.add("Delhi_1 ");
        centers.add("Delhi_2 ");
        centers.add("Delhi_3 ");
        centers.add("Delhi_4 ");

        ArrayAdapter<String> dt = new ArrayAdapter<String>(mark_attendance.this, android.R.layout.simple_spinner_item, centers);
        dt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dt);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item=parent.getItemAtPosition(position).toString();
        System.out.println("Center chosen:"+item);
        report.edit().putString("center chosen", item).apply();
        System.out.println("center chosen stored");
        Toast.makeText(parent.getContext(), "Selected:"+item , Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(parent.getContext(), "No selection made" , Toast.LENGTH_LONG).show();
    }

    public void submit3(View view){

        Intent i=new Intent(getApplicationContext(),volun_select2.class);
        startActivity(i);
    }}
