package com.shwetasrivastava.twp;

import android.content.Context;
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

import static com.shwetasrivastava.twp.R.id.sc_list;

public class view_schedule extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseReference myRef;
    FirebaseDatabase database;
    Spinner spinner14;
    SharedPreferences sch_choice;
    ArrayList<String> schedule=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Schedule");
        spinner14=(Spinner)findViewById(R.id.spinner14);
        sch_choice= this.getSharedPreferences("com.shwetasrivastava.twp", Context.MODE_PRIVATE);

        schedule.clear();
        SharedPreferences.Editor editor=getSharedPreferences("com.shwetasrivastava.twp", Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
        Log.i("Shared Pref","CLEAR!!!!!!!!!!");

        spinner14.setOnItemSelectedListener(this);
        ArrayList<String> centers = new ArrayList<String>();
        centers.add("");
        centers.add("Delhi 1 ");
        centers.add("Delhi 2 ");
        centers.add("Delhi 3 ");
        centers.add("Delhi 4 ");
        ArrayAdapter<String> data_5= new ArrayAdapter<String>(view_schedule.this, android.R.layout.simple_spinner_item, centers);
        data_5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner14.setAdapter(data_5);
    }

    public void schedule(View view){

        String item= sch_choice.getString("center chosen","default").toString();
        System.out.println("yo wprking"+item);
        myRef.child(item).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String name=child.getKey();
                    String value = child.getValue(String.class);
                    System.out.println("Value working" + name + value);
                    schedule.add(name+"-"+value);

                }
                System.out.println(schedule);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                System.out.print("error is"+ databaseError);

            }
        });

        ListView sc_list=(ListView)findViewById(R.id.sc_list);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(view_schedule.this, android.R.layout.simple_list_item_1, schedule);
        sc_list.setAdapter(adapter2);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(), "Selected: " + parent.getItemAtPosition(position), Toast.LENGTH_LONG).show();
        sch_choice.edit().putString("center chosen", parent.getItemAtPosition(position).toString()).apply();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        Toast.makeText(parent.getContext(), "Selected a center!", Toast.LENGTH_LONG).show();

    }
}
