package com.w2s.orhan.where2stay.Advert;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.w2s.orhan.where2stay.R;

import java.io.IOException;
import java.util.UUID;

public class add_typeOne extends AppCompatActivity {

    EditText textTitle;
    EditText textCost;
    ImageView imgAdvert;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;

    Uri selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_type_one);

        // activity variables
        textTitle = findViewById(R.id.et_title1);
        textCost = findViewById(R.id.et_cost1);
        imgAdvert = findViewById(R.id.iv_image1);

        // fire base variables
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();

    }

    public void chooseImage(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
            } else {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,2);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1) {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,2);
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 2 && resultCode == RESULT_OK && data != null) {
            selected = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selected);
                imgAdvert.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void addAdvert(View view) {
        UUID uuidImage = UUID.randomUUID();
        String imageName = "images/"+uuidImage+".jpg";
        StorageReference storageReference = mStorageRef.child(imageName);
        storageReference.putFile(selected).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String downloadURL = taskSnapshot.getDownloadUrl().toString();

                // veritabanına eposta ve yorumu kaydedeceğiz
                FirebaseUser user = mAuth.getCurrentUser();
                String userEmail = user.getEmail().toString();
                String advTitle = textTitle.getText().toString();
                String advCost = textCost.getText().toString();
                String userID = user.getUid();

                // post için eşsiz id
                UUID uuid = UUID.randomUUID();
                String uuidString = uuid.toString();

                // veritabanına ekliyoruz
                databaseReference.child("Posts").child("kiralık").child(uuidString).child("usermail").setValue(userEmail);
                databaseReference.child("Posts").child("kiralık").child(uuidString).child("title").setValue(advTitle);
                databaseReference.child("Posts").child("kiralık").child(uuidString).child("cost").setValue(advCost);
                databaseReference.child("Posts").child("kiralık").child(uuidString).child("downloadurl").setValue(downloadURL);
                databaseReference.child("Posts").child("kiralık").child(uuidString).child("user_id").setValue(userID);


                Toast.makeText(getApplicationContext(), "Post Shared!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
