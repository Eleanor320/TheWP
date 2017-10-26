package com.shwetasrivastava.twp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class upload_resources extends AppCompatActivity {

    private static final int READ_REQUEST_CODE = 1994;
    private StorageReference mStorageRef;
    ArrayList<Uri> files=new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Resources");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_resources);
        mStorageRef = FirebaseStorage.getInstance().getReference();

    }

    public void upload(View view) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent, READ_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        Log.i("upload_resources", "Recieved an \"Activity Result\" ");
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                final Uri link=uri;
                Log.i("upload resources", "URI" + uri.toString());
                StorageReference riversRef = mStorageRef.child(uri.getLastPathSegment());
                riversRef.putFile(uri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Log.i("upload_resources", "SUCCESSFUL!!");
                                Toast.makeText(upload_resources.this, "Upload Successful!",Toast.LENGTH_SHORT).show();
                                myRef.child(link.getLastPathSegment()).setValue(link.getLastPathSegment());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Log.i("upload_resources", "UNSUCCESSFUL");
                                Toast.makeText(upload_resources.this, "Upload Unsuccessful! Try Again!",Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        }
    }
}