package com.shwetasrivastava.twp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class volunteer_page extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_page);
    }

    public void onclick(View view){

    int i=view.getId();
    Intent intent;

        if(i==R.id.upload_progress){
        intent=new Intent(getApplicationContext(),upload_progress.class);
        startActivity(intent);
    }
        else if (i==R.id.download_resources){
        intent=new Intent(getApplicationContext(),download_resources.class);
        startActivity(intent);
    }
        else if (i==R.id.view_attendance){
        intent=new Intent(getApplicationContext(),view_attendance.class);
        startActivity(intent);
    }
        else if (i==R.id.view_schedule){
        intent=new Intent(getApplicationContext(),view_schedule.class);
        startActivity(intent);
    }

        else if (i==R.id.upload_volun_report){
            intent=new Intent(getApplicationContext(),upload_volun_progress.class);
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
