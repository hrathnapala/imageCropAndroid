package com.example.imagecrop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;



import java.net.URI;

public class MainActivity extends AppCompatActivity {

    EditText editName,editAge,editPhone;
    Button saveRecord,recordList;
    ImageView imageView;
    final int REQUEST_CODE_GALLERY = 999;
    public static final int PICK_IMAGE = 1;
    Uri imgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editPhone = findViewById(R.id.editPhone);
        saveRecord = findViewById(R.id.saveRecord);
        recordList = findViewById(R.id.recordList);
        imageView = findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallary = new Intent();
                gallary.setType("image/*");
                gallary.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallary,"select picture"),PICK_IMAGE);

            }
        });
        saveRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageView.getDrawable() == null){
                    Toast.makeText(MainActivity.this, "Sorry", Toast.LENGTH_SHORT).show();
                }
               // Toast.makeText(MainActivity.this, imageView.getDrawable().toString(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){
          imgUri = data.getData();
          try {
              Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imgUri);
              imageView.setImageBitmap(bitmap);
          }catch (Exception e){
              e.printStackTrace();
          }
        }
    }
}
