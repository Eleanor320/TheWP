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

public class volun_select2 extends AppCompatActivity  {

    Query myRef;
    DatabaseReference database,db2;
    private Calendar calendar;
    private int year, month, day;
    SharedPreferences report;
    final ArrayList<String> volunteers=new ArrayList<>();
    ArrayList<String> v2=new ArrayList<>();
    SharedPreferences volun;
    EditText date,attendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volun_select2);
        database = FirebaseDatabase.getInstance().getReference("Volunteers");
        db2=FirebaseDatabase.getInstance().getReference("Attendance");
        volun= this.getSharedPreferences("com.shwetasrivastava.twp", Context.MODE_PRIVATE);
        date=(EditText)findViewById(R.id.editText3);
        attendance=(EditText)findViewById(R.id.editText7);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

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

        ListView ll=(ListView)findViewById(R.id.ll1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(volun_select2.this, android.R.layout.simple_list_item_1, volunteers);
        ll.setAdapter(adapter2);
        ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            System.out.println("person clicked"+volunteers.get(position));
            v2.add(volunteers.get(position));
            Toast.makeText(parent.getContext(), "Selected: " + parent.getItemAtPosition(position), Toast.LENGTH_LONG).show();

            }
        });
     }

    private void showDate(int year, int month, int day) {
        date.setText(day+"/"+month+"/"+year);
        volun.edit().putString("date chosen", "0"+day+"0"+month+"0"+year).apply();
    }

    public void ok(View view){

        for(int i=0;i<=(v2.size()-1);i++){
            attendance.append(v2.get(i)+"");
        }
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

    public void fill_report(View view){

        System.out.println("date chosen"+volun.getString("date chosen","default"));
        System.out.println(attendance.getText().toString());
        attend attend=new attend(volun.getString("date chosen","default"),attendance.getText().toString());
        db2.child(report.getString("center chosen","default").toString()).child(volun.getString("date chosen","default")).setValue(attend);

        SharedPreferences.Editor editor=getSharedPreferences("com.shwetasrivastava.page2", Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
        Log.i("Shared Pref","CLEAR!!!!!!!!!!");

        Intent i=new Intent(getApplicationContext(),mark_attendance.class);
        startActivity(i);


    }
}
