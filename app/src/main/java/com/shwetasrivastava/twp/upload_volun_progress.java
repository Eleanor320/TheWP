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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class upload_volun_progress extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinn15;
    FirebaseDatabase database;
    DatabaseReference myRef;
    SharedPreferences report2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_volun_progress);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Volunteers");
        report2= this.getSharedPreferences("com.shwetasrivastava.twp", Context.MODE_PRIVATE);
        spinn15=(Spinner)findViewById(R.id.spinn15);
        spinn15.setOnItemSelectedListener(this);
        ArrayList<String> centers = new ArrayList<String>();
        centers.add("");
        centers.add("Delhi_1 ");
        centers.add("Delhi_2 ");
        centers.add("Delhi_3 ");
        centers.add("Delhi_4 ");

        ArrayAdapter<String> data_4 = new ArrayAdapter<String>(upload_volun_progress.this, android.R.layout.simple_spinner_item, centers);
        data_4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinn15.setAdapter(data_4);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item=parent.getItemAtPosition(position).toString();
        System.out.println("Center chosen:"+item);
        report2.edit().putString("center chosen", item).apply();
        System.out.println("center chosen stored");

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(parent.getContext(), "No selection made" , Toast.LENGTH_LONG).show();
    }

    public void create_spinner(View view){
     /*   Intent i=new Intent(getApplicationContext(),volun_select.class);
        startActivity(i);
    */}
}
