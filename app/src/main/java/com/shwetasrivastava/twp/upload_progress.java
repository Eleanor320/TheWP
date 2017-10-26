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
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class upload_progress extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spin_6;
    FirebaseDatabase database;
    DatabaseReference myRef,myRef2;
    SharedPreferences report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_progress);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Students");
        report= this.getSharedPreferences("com.shwetasrivastava.twp", Context.MODE_PRIVATE);
        spin_6=(Spinner)findViewById(R.id.spin_6);
        spin_6.setOnItemSelectedListener(this);
        ArrayList<String> centers = new ArrayList<String>();
        centers.add("");
        centers.add("Delhi_1 ");
        centers.add("Delhi_2 ");
        centers.add("Delhi_3 ");
        centers.add("Delhi_4 ");

        ArrayAdapter<String> data = new ArrayAdapter<String>(upload_progress.this, android.R.layout.simple_spinner_item, centers);
        data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_6.setAdapter(data);
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

    public void create_spinner(View view){
        Intent i=new Intent(getApplicationContext(),student_select.class);
        startActivity(i);
    }

}
