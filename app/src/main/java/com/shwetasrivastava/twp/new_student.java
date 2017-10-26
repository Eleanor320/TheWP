package com.shwetasrivastava.twp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class new_student extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button submit;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Spinner spinner19, spinner12;
    EditText s_name;
    SharedPreferences choice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        submit=(Button)findViewById(R.id.submit);
        s_name=(EditText) findViewById(R.id.s_name);
        spinner19=(Spinner)findViewById(R.id.spinner19);
        spinner12=(Spinner)findViewById(R.id.spinner12);

        choice= this.getSharedPreferences("com.shwetasrivastava.twp", Context.MODE_PRIVATE);

        spinner19.setOnItemSelectedListener(this);
        spinner12.setOnItemSelectedListener(this);
        ArrayList<String> centers = new ArrayList<String>();
        centers.add("");
        centers.add("Delhi 1 ");
        centers.add("Delhi 2 ");
        centers.add("Delhi 3 ");
        centers.add("Delhi 4 ");

        ArrayList<String> levels = new ArrayList<String>();
        levels.add("");
        levels.add("Word ");
        levels.add("Paragraph");
        levels.add("Story");
        levels.add("Book ");

        ArrayAdapter<String> data_1 = new ArrayAdapter<String>(new_student.this, android.R.layout.simple_spinner_item, centers);
        ArrayAdapter<String> data_2 = new ArrayAdapter<String>(new_student.this, android.R.layout.simple_spinner_item, levels);
        data_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        data_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner19.setAdapter(data_1);
        spinner12.setAdapter(data_2);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item;
        switch(parent.getId()){
            case R.id.spinner19: item = parent.getItemAtPosition(position).toString();
                                // Showing selected spinner item
                                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                Log.i("Center chosen:",item);
                                choice.edit().putString("center chosen", item).apply();
                                break;
            case R.id.spinner12: item = parent.getItemAtPosition(position).toString();
                                // Showing selected spinner item
                                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                Log.i("level chosen:",item);
                                choice.edit().putString("level chosen", item).apply();
                                break;
            default : Toast.makeText(parent.getContext(), "no selection made!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        Toast.makeText(parent.getContext(), "Make a selection!", Toast.LENGTH_LONG).show();

    }

    public void submit1(View view){

        database = FirebaseDatabase.getInstance();
        myRef=database.getReference("Students");
        s_name=(EditText)findViewById(R.id.s_name);

        Student student=new Student(s_name.getText().toString(),choice.getString("level chosen","default"));
        myRef.child(choice.getString("center chosen","default")).child(s_name.getText().toString()).setValue(student);

        Intent i=new Intent(getApplicationContext(),admin_page.class);
        startActivity(i);
        SharedPreferences.Editor editor=getSharedPreferences("com.shwetasrivastava.twp", Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
        Log.i("Shared Pref","CLEAR!!!!!!!!!!");

    }
}
