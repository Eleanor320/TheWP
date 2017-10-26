package com.shwetasrivastava.twp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class view_attendance extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Query myRef;
    DatabaseReference database;
    Spinner spinner21;
    private Calendar calendar;
    private int year, month, day;
    SharedPreferences att;
    EditText date2;
    ArrayList<String> attended=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);
        attended.clear();
        database = FirebaseDatabase.getInstance().getReference("Attendance");

        date2=(EditText)findViewById(R.id.editText5);
        spinner21=(Spinner)findViewById(R.id.spinner21);
        spinner21.setOnItemSelectedListener(this);
        ArrayList<String> centers = new ArrayList<String>();
        centers.add("");
        centers.add("Delhi_1 ");
        centers.add("Delhi_2 ");
        centers.add("Delhi_3 ");
        centers.add("Delhi_4 ");

        ArrayAdapter<String> dt1 = new ArrayAdapter<String>(view_attendance.this, android.R.layout.simple_spinner_item, centers);
        dt1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner21.setAdapter(dt1);

        att= this.getSharedPreferences("com.shwetasrivastava.twp", Context.MODE_PRIVATE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(parent.getContext(), "Selected: " + parent.getItemAtPosition(position), Toast.LENGTH_LONG).show();
        att.edit().putString("center chosen",parent.getItemAtPosition(position).toString()).apply();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        Toast.makeText(parent.getContext(), "Select!", Toast.LENGTH_LONG).show();
    }

    private void showDate(int year, int month, int day) {
        date2.setText(day+"/"+month+"/"+year);
        att.edit().putString("date chosen", "0"+day+"0"+month+"0"+year).apply();
    }

    @SuppressWarnings("deprecation")
    public void setdate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "select date",Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    public void ok2(View view){

        System.out.println(att.getString("center chosen","default")+att.getString("date chosen","default"));
        database.child(att.getString("center chosen","default")).child(att.getString("date chosen","default")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String attend=dataSnapshot.child("attend").getValue().toString();
                System.out.println("YOOOOOOOOO"+attend);
                attended.add(attend);

                System.out.println(attended);
                ListView l2=(ListView)findViewById(R.id.l2);
                ArrayAdapter<String> adapter21 = new ArrayAdapter<String>(view_attendance.this, android.R.layout.simple_list_item_1, attended);
                l2.setAdapter(adapter21);

            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

    }
}
