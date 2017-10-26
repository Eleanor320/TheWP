package com.shwetasrivastava.twp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class upload_schedule extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Calendar calendar;
    private EditText dateView,timeView;
    private int year, month, day;
    SharedPreferences sch_date;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_schedule);
        spin=(Spinner)findViewById(R.id.spinner3);

        sch_date= this.getSharedPreferences("com.shwetasrivastava.twp", Context.MODE_PRIVATE);

        timeView=(EditText)findViewById(R.id.editText);
        dateView = (EditText) findViewById(R.id.editText2);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        spin.setOnItemSelectedListener(this);
        ArrayList<String> centers = new ArrayList<String>();
        centers.add("");
        centers.add("Delhi 1 ");
        centers.add("Delhi 2 ");
        centers.add("Delhi 3 ");
        centers.add("Delhi 4 ");
        ArrayAdapter<String> data_3 = new ArrayAdapter<String>(upload_schedule.this, android.R.layout.simple_spinner_item, centers);
        data_3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(data_3);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(parent.getContext(), "Select a center!", Toast.LENGTH_LONG).show();

    }

    @SuppressWarnings("deprecation")
    public void setdate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "date",Toast.LENGTH_SHORT)
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

    private void showDate(int year, int month, int day) {
        dateView.setText(day+"/"+month+"/"+year);
        sch_date.edit().putString("date chosen", "0"+day+"0"+month+"0"+year).apply();
    }

    public void add_class(View view){

        database = FirebaseDatabase.getInstance();
        myRef=database.getReference("Schedule");

        myRef.child(sch_date.getString("center chosen","default")).child(sch_date.getString("date chosen","default")).setValue(timeView.getText().toString());

        Intent i=new Intent(getApplicationContext(),admin_page.class);
        startActivity(i);
        SharedPreferences.Editor editor=getSharedPreferences("com.shwetasrivastava.page2", Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
        Log.i("Shared Pref","CLEAR!!!!!!!!!!");
    }
}
