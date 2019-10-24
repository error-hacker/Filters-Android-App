package com.devsahil.filters;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import static com.squareup.picasso.Picasso.*;

public class ControlActivity extends AppCompatActivity {

    ImageView mCenterImageView;

    final static int PICK_IMAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        mCenterImageView = (ImageView) findViewById(R.id.centerImageView);

        mCenterImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                //intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
    }

    public void onClickCheck(View view) {
        Intent intent = new Intent(this, ImagePreviewActivty.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode==PICK_IMAGE && resultCode== Activity.RESULT_OK) {
            Uri selectedImageUri = data.getData();

            //mCenterImageView.setImageBitmap();

            Picasso.get().load(selectedImageUri).fit().centerInside().into(mCenterImageView);

            //Picasso.load(selectedImageUri).into(mCenterImageView);
        }
    }
}
