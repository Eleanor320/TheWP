package com.shwetasrivastava.twp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class download_resources extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Resources");
    ArrayList resources=new ArrayList();
    Spinner spinner13;
    private StorageReference mStorageRef,mst;
    SharedPreferences file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_resources);
        spinner13=(Spinner)findViewById(R.id.spinner13);
        spinner13.setOnItemSelectedListener(download_resources.this);
        file= this.getSharedPreferences("com.shwetasrivastava.twp", Context.MODE_PRIVATE);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child:dataSnapshot.getChildren()) {
                    String value =  child.getValue().toString();
                    System.out.println("Value is: " + value);
                    resources.add(value);
                }
                System.out.println("files list-"+resources);
                ArrayAdapter<String> dataAda = new ArrayAdapter<String>(download_resources.this, android.R.layout.simple_spinner_item, resources);
                dataAda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner13.setAdapter(dataAda);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println("Failed to read value."+ error.toException());
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

        String item = parent.getItemAtPosition(position).toString();
        file.edit().putString("file chosen", item).apply();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(parent.getContext(), "Select a file!", Toast.LENGTH_LONG).show();

    }

    public void download(View view){

        mst=mStorageRef.child(file.getString("file chosen","default").toString());
        System.out.println(mst.getPath());
        System.out.println(getFilesDir());
        System.out.println(getCacheDir());
        System.out.println(getBaseContext().getCacheDir());
        System.out.println(getBaseContext().getFilesDir());
        System.out.println(getBaseContext().getExternalCacheDir());


        File localFile = null;
        try {
            localFile = File.createTempFile("file", "pdf", getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
            mst.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            System.out.println("Download Done!!!!!");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    System.out.println("Download Failed!!!!!");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
