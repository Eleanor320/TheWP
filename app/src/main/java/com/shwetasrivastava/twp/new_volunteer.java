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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class new_volunteer extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button submit;
    TextView name;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Spinner spinner;
    EditText v_name, email, phone;
    SharedPreferences choice;
    int attendance=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_volunteer);

        submit=(Button)findViewById(R.id.submit);
        name=(TextView)findViewById(R.id.name);
        spinner=(Spinner)findViewById(R.id.spinner);

        choice= this.getSharedPreferences("com.shwetasrivastava.twp", Context.MODE_PRIVATE);

        spinner.setOnItemSelectedListener(this);
        ArrayList<String> centers = new ArrayList<String>();
        centers.add("");
        centers.add("Delhi 1 ");
        centers.add("Delhi 2 ");
        centers.add("Delhi 3 ");
        centers.add("Delhi 4 ");

        ArrayAdapter<String> data_v = new ArrayAdapter<String>(new_volunteer.this, android.R.layout.simple_spinner_item, centers);
        data_v.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(data_v);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        choice.edit().putString("center chosen", item).apply();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        Toast.makeText(parent.getContext(), "Select a center!", Toast.LENGTH_LONG).show();

    }

    public void submit2(View view){

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Volunteers");

        v_name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        email=(EditText)findViewById(R.id.email);

        Volunteer volunteer = new Volunteer(v_name.getText().toString(), email.getText().toString(), phone.getText().toString(),attendance,choice.getString("center chosen","default"));
        myRef.child(choice.getString("center chosen","default")).child(v_name.getText().toString()).setValue(volunteer);

        Intent i=new Intent(getApplicationContext(),admin_page.class);
        startActivity(i);
        SharedPreferences.Editor editor=getSharedPreferences("com.shwetasrivastava.twp", Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
        Log.i("Shared Pref","CLEAR!!!!!!!!!!");
    }
}
