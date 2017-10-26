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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class fill_report extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Intent intent;
    Spinner spin4,spin7,spin8,spin9,spin10;
    SharedPreferences s_report2;
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_report);
        Intent i=getIntent();
        System.out.println("yo working"+i.getStringExtra("student selected"));
        s_report2=this.getSharedPreferences("com.shwetasrivastava.twp", Context.MODE_PRIVATE);

        spin4=(Spinner)findViewById(R.id.spinner4);//class number
        spin4.setOnItemSelectedListener(this);
        spin7=(Spinner)findViewById(R.id.spinner7);//one
        spin7.setOnItemSelectedListener(this);
        spin8=(Spinner)findViewById(R.id.spinner8);//two
        spin8.setOnItemSelectedListener(this);
        spin9=(Spinner)findViewById(R.id.spinner9);//three
        spin9.setOnItemSelectedListener(this);
        spin10=(Spinner)findViewById(R.id.spinner10);//four
        spin10.setOnItemSelectedListener(this);
        text=(EditText)findViewById(R.id.editText4);

        ArrayList<String> c0 = new ArrayList<>();
        for(int i1=1;i1<=30;i1++){
        c0.add(String.valueOf(i1));
        }

        ArrayList<String> c1 = new ArrayList<String>();
        c1.add("Child not at Reading Level");
        c1.add("Class did not involve reading");
        c1.add("1");
        c1.add("2");
        c1.add("3");
        c1.add("More than 3");

        ArrayList<String> c2 = new ArrayList<String>();
        c2.add("N/A (child not my regular student)");
        c2.add("N/A (class did not test any previous material)");
        c2.add("Weak");
        c2.add("Tries hard but needed support");
        c2.add("Good");

        ArrayList<String> c3 = new ArrayList<String>();
        c3.add("My class did not require any participation");
        c3.add("Weak and uninterested in learning");
        c3.add("Weak but shows some interest to learn");
        c3.add("Was engaged at some parts");
        c3.add("Was engaged through the session");
        c3.add("Good comprehension and involvement");

        ArrayList<String> c4 = new ArrayList<String>();
        c4.add("Still learning letters");
        c4.add("can identify and comprehend some words");
        c4.add("struggles with simple sentences");
        c4.add("Tries hard but needed support");
        c4.add("L1");
        c4.add("L2");
        c4.add("L3");

        ArrayAdapter<String> dataAdapter0 = new ArrayAdapter<String>(fill_report.this, android.R.layout.simple_spinner_item, c0);
        dataAdapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//class number

        ArrayAdapter<String> dataA = new ArrayAdapter<String>(fill_report.this, android.R.layout.simple_spinner_item, c1);
        dataA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//one

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(fill_report.this, android.R.layout.simple_spinner_item, c2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//two


        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(fill_report.this, android.R.layout.simple_spinner_item, c3);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(fill_report.this, android.R.layout.simple_spinner_item, c4);
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin4.setAdapter(dataAdapter0);//class number
        spin7.setAdapter(dataA);//one
        spin8.setAdapter(dataAdapter2);//two
        spin9.setAdapter(dataAdapter3);//three
        spin10.setAdapter(dataAdapter4);//four
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item;
        if(parent.getId()==R.id.spinner4)
        {
            System.out.println("Class number"+parent.getItemAtPosition(position).toString());
            item = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            s_report2.edit().putString("class number",item).apply();

        }
        else if(parent.getId()==R.id.spinner7)
        {
            System.out.println("one"+parent.getItemAtPosition(position).toString());
            item = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            s_report2.edit().putString("one",item).apply();

        }
        else if(parent.getId()==R.id.spinner8)
        {
            System.out.println("two"+parent.getItemAtPosition(position).toString());
            item = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            s_report2.edit().putString("two",item).apply();

        }
        else if(parent.getId()==R.id.spinner9)
        {
            System.out.println("three"+parent.getItemAtPosition(position).toString());
            item = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            s_report2.edit().putString("three",item).apply();

        }
        else if(parent.getId()==R.id.spinner10)
        {
            System.out.println("four"+parent.getItemAtPosition(position).toString());
            item = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            s_report2.edit().putString("four",item).apply();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        Toast.makeText(parent.getContext(), "Select a choice!", Toast.LENGTH_LONG).show();

    }

    public void add_report(View view){

        String c_no,one,two,three,four,five;
        c_no=s_report2.getString("class number","default").toString();
        one=s_report2.getString("one","default").toString();
        two=s_report2.getString("two","default").toString();
        three=s_report2.getString("three","default").toString();
        four=s_report2.getString("four","default").toString();
        Intent i=getIntent();
        System.out.println("yo working"+i.getStringExtra("student selected"));
        System.out.println(c_no);
        System.out.println(one);
        System.out.println(two);
        System.out.println(three);
        System.out.println(four);
        System.out.println(text.getText().toString());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("s_report");

        Stud_report sreport=new Stud_report(i.getStringExtra("student selected"),c_no,one,two,three,four,text.getText().toString());

        myRef.child(i.getStringExtra("student selected")).child(c_no).setValue(sreport);

        SharedPreferences.Editor editor=getSharedPreferences("com.shwetasrivastava.twp", Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
        Log.i("Shared Pref","CLEAR!!!!!!!!!!");

        Intent i2=new Intent(getApplicationContext(),student_select.class);
        startActivity(i2);


    }
}
