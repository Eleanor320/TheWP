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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class fill_report2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spin18,spin6,spin11,spin15,spin16,spin17;
    SharedPreferences v_report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_report2);

        Intent i=getIntent();
        System.out.println("yo working"+i.getStringExtra("volunteer selected"));
        v_report=this.getSharedPreferences("com.shwetasrivastava.twp", Context.MODE_PRIVATE);

        spin18=(Spinner)findViewById(R.id.spinner18);
        spin18.setOnItemSelectedListener(this);//duration
        spin6=(Spinner)findViewById(R.id.spinner6);
        spin6.setOnItemSelectedListener(this);//one
        spin11=(Spinner)findViewById(R.id.spinner11);
        spin11.setOnItemSelectedListener(this);//two
        spin15=(Spinner)findViewById(R.id.spinner15);
        spin15.setOnItemSelectedListener(this);//three
        spin16=(Spinner)findViewById(R.id.spinner16);
        spin16.setOnItemSelectedListener(this);//four
        spin17=(Spinner)findViewById(R.id.spinner17);
        spin17.setOnItemSelectedListener(this);

        ArrayList<String> c0 = new ArrayList<>();
        c0.add("Jan 1-Jan 15");
        c0.add("Jan 16-Jan 31");
        c0.add("Feb 1-Feb 15");
        c0.add("Feb 16-Feb 29");
        c0.add("Mar 1-Mar 15");
        c0.add("Mar 16-Mar 31");
        c0.add("Apr 1-Apr 15");
        c0.add("Apr 16-Apr 30");
        c0.add("May 1-May 15");
        c0.add("May 16-May 31");
        c0.add("Jun 1-Jun 15");
        c0.add("Jun 16-Jun 30");
        c0.add("Jul 1-Jul 15");
        c0.add("Jul 16-Jul 31");
        c0.add("Aug 1-Aug 15");
        c0.add("Aug 16-Aug 31");
        c0.add("Sept 1-Sept 15");
        c0.add("Sept 16-Sept 30");
        c0.add("Oct 1-Oct 15");
        c0.add("Oct 16-Oct 31");
        c0.add("Nov 1-Nov 15");
        c0.add("Nov 16-Nov 30");
        c0.add("Dec 1-Dec 15");
        c0.add("Dec 16-Dec 31");

        ArrayList<String> c1 = new ArrayList<String>();
        c1.add("0");
        c1.add("1");
        c1.add("2");
        c1.add("3");
        c1.add("4");
        c1.add("5");

        ArrayList<String> c2 = new ArrayList<String>();
        c2.add("Between 0-5");
        c2.add("Between 5-15");
        c2.add("Between 15-25");
        c2.add("More than 25");

        ArrayList<String> c3 = new ArrayList<String>();
        c3.add("None");
        c3.add("1");
        c3.add("2");
        c3.add("3");
        c3.add("4");
        c3.add("5 or more");

        ArrayList<String> c4 = new ArrayList<String>();
        c4.add("Yes");
        c4.add("No");

        ArrayList<String> c5 = new ArrayList<String>();
        c4.add("None");
        c4.add("A little");
        c4.add("Unsure");
        c4.add("A lot");

        ArrayAdapter<String> data_0 = new ArrayAdapter<String>(fill_report2.this, android.R.layout.simple_spinner_item, c0);
        data_0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//duration

        ArrayAdapter<String> data_Adapter = new ArrayAdapter<String>(fill_report2.this, android.R.layout.simple_spinner_item, c1);
        data_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//one

        ArrayAdapter<String> data_Adapter2 = new ArrayAdapter<String>(fill_report2.this, android.R.layout.simple_spinner_item, c2);
        data_Adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//two


        ArrayAdapter<String> data_Adapter3 = new ArrayAdapter<String>(fill_report2.this, android.R.layout.simple_spinner_item, c3);
        data_Adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//three

        ArrayAdapter<String> data_Adapter4 = new ArrayAdapter<String>(fill_report2.this, android.R.layout.simple_spinner_item, c4);
        data_Adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//four

        ArrayAdapter<String> data_Adapter5 = new ArrayAdapter<String>(fill_report2.this, android.R.layout.simple_spinner_item, c5);
        data_Adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//five

        spin18.setAdapter(data_0);
        spin6.setAdapter(data_Adapter);
        spin11.setAdapter(data_Adapter2);
        spin15.setAdapter(data_Adapter3);
        spin16.setAdapter(data_Adapter4);
        spin17.setAdapter(data_Adapter5);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item;
        if(parent.getId()==R.id.spinner18)
        {
            System.out.println("Duration"+parent.getItemAtPosition(position).toString());
            item = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            v_report.edit().putString("duration",item).apply();

        }
        else if(parent.getId()==R.id.spinner6)
        {
            System.out.println("one"+parent.getItemAtPosition(position).toString());
            item = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            v_report.edit().putString("one",item).apply();

        }
        else if(parent.getId()==R.id.spinner11)
        {
            System.out.println("two"+parent.getItemAtPosition(position).toString());
            item = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            v_report.edit().putString("two",item).apply();

        }
        else if(parent.getId()==R.id.spinner15)
        {
            System.out.println("three"+parent.getItemAtPosition(position).toString());
            item = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            v_report.edit().putString("three",item).apply();

        }
        else if(parent.getId()==R.id.spinner16)
        {
            System.out.println("four"+parent.getItemAtPosition(position).toString());
            item = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            v_report.edit().putString("four",item).apply();
        }
        else if(parent.getId()==R.id.spinner17)
        {
            System.out.println("five"+parent.getItemAtPosition(position).toString());
            item = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            v_report.edit().putString("five",item).apply();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(parent.getContext(), "Select a choice!", Toast.LENGTH_LONG).show();
    }

    public void add_report(View view){

        String dur,one,two,three,four,five;
        dur=v_report.getString("duration","default").toString();
        one=v_report.getString("one","default").toString();
        two=v_report.getString("two","default").toString();
        three=v_report.getString("three","default").toString();
        four=v_report.getString("four","default").toString();
        five=v_report.getString("five","default").toString();
        Intent i=getIntent();
        System.out.println("yo working"+i.getStringExtra("volunteer selected"));
        System.out.println(dur);
        System.out.println(one);
        System.out.println(two);
        System.out.println(three);
        System.out.println(four);
        System.out.println(five);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("v_report");

        Stud_report vreport=new Stud_report(i.getStringExtra("volunteer selected"),dur,one,two,three,four,five);

        myRef.child(i.getStringExtra("volunteer selected")).child(dur).setValue(vreport);

        SharedPreferences.Editor editor=getSharedPreferences("com.shwetasrivastava.twp", Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
        Log.i("Shared Pref","CLEAR!!!!!!!!!!");

        Intent I=new Intent(getApplicationContext(),volun_select.class);
        startActivity(I);



    }
}
