package com.devsahil.filters;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

public class ControlActivity extends AppCompatActivity {

    ImageView mCenterImageView;

    ImageView mFirstImageView;
    ImageView mSecondImageView;
    ImageView mThirdImageView;
    ImageView mFourthImageView;

    final static int PICK_IMAGE = 2;
    final static int MY_REQUEST_STORAGE_PERMISSION = 3;

    private static final String TAG = ControlActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        mCenterImageView = findViewById(R.id.centerImageView);
        mFirstImageView = findViewById(R.id.imageView4);
        mSecondImageView = findViewById(R.id.imageView5);
        mThirdImageView = findViewById(R.id.imageView6);
        mFourthImageView = findViewById(R.id.imageView7);

        mCenterImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(ControlActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    if(ActivityCompat.shouldShowRequestPermissionRationale(ControlActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                        //show user a message
                    } else{
                        ActivityCompat.requestPermissions(ControlActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_REQUEST_STORAGE_PERMISSION);
                    }
                    return;
                }

                Intent intent =new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");

                //intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        switch (requestCode){
            case MY_REQUEST_STORAGE_PERMISSION:
                if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //show a message
                } else{
                    Log.d(TAG,"Permission Denied!");
                }
        }
    }

    public void onClickCheck(View view) {
        Intent intent = new Intent(this, ImagePreviewActivty.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode==PICK_IMAGE && resultCode== Activity.RESULT_OK) {
            Uri selectedImageUri = data.getData();


            /*try {
                Bitmap bitmap;
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                // Log.d(TAG, String.valueOf(bitmap));
                mCenterImageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }*/

            Picasso.get().load(selectedImageUri).fit().centerInside().into(mCenterImageView);

            Picasso.get().load(selectedImageUri).fit().centerInside().into(mFirstImageView);
            Picasso.get().load(selectedImageUri).fit().centerInside().into(mSecondImageView);
            Picasso.get().load(selectedImageUri).fit().centerInside().into(mThirdImageView);
            Picasso.get().load(selectedImageUri).fit().centerInside().into(mFourthImageView);

            //Picasso.load(selectedImageUri).into(mCenterImageView);
        }
    }
}
