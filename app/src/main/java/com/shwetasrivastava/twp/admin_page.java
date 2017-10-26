package com.shwetasrivastava.twp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class admin_page extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

    }

    public void choose(View view){

        int i=view.getId();
        Intent intent;

        if(i==R.id.attendance){
            intent=new Intent(getApplicationContext(),mark_attendance.class);
            startActivity(intent);
        }
        else if (i==R.id.new_student){
            intent=new Intent(getApplicationContext(),new_student.class);
            startActivity(intent);
        }
        else if (i==R.id.new_volunteer){
            intent=new Intent(getApplicationContext(),new_volunteer.class);
            startActivity(intent);
        }
        /*else if (i==R.id.view_progress){
            intent=new Intent(getApplicationContext(),view_progress.class);
            startActivity(intent);
        }*/
        else if (i==R.id.upload_resources){
            intent=new Intent(getApplicationContext(),upload_resources.class);
            startActivity(intent);
        }
        else if (i==R.id.upload_schedule){
            intent=new Intent(getApplicationContext(),upload_schedule.class);
            startActivity(intent);
        }
        else if (i==R.id.signout){
            intent=new Intent(getApplicationContext(),LogIn.class);
            mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            System.out.println("Signed out!!!!");
            startActivity(intent);
        }
    }


}

