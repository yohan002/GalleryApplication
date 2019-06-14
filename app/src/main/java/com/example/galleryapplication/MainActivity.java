package com.example.galleryapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<File> list;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView)findViewById(R.id.image_grid);

        list = imageReader(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/Basic"));

        gridView.setAdapter(new gridAdapter());

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this,FullImageActivity.class);
                intent.putExtra("img",list.get(i).toString());

                startActivity(intent);

            }
        });


    }

    public class gridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View convertView = null;

            if(convertView == null)
            {
                convertView = getLayoutInflater().inflate(R.layout.row_layout,viewGroup,false);
                ImageView myImage = (ImageView) convertView.findViewById(R.id.my_Image);
                myImage.setImageURI(Uri.parse(list.get(i).toString()));
            }

            return convertView;
        }
    }


    private ArrayList<File> imageReader(File externalStorageDirectory) {

        ArrayList<File> b = new ArrayList<>();

        File[] files = externalStorageDirectory.listFiles();

        for(int i =0;i<files.length;i++)
        {
            if(files[i].isDirectory())
            {
                b.addAll(imageReader(files[i]));
            }
            else
            {
                if(files[i].getName().endsWith(".jpg"))
                {
                    b.add(files[i]);
                }
            }
        }

        return b;
    }
}
