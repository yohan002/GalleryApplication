package com.example.galleryapplication;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class FullImageActivity extends AppCompatActivity {

    ImageView fullImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        fullImage = (ImageView)findViewById(R.id.full_image);

        String data = getIntent().getExtras().getString("img");

        fullImage.setImageURI(Uri.parse(data));
    }
}
