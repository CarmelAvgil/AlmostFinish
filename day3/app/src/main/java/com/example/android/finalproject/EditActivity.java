package com.example.android.finalproject;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.net.URL;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    EditText title;
    EditText description;
    EditText url1;
    LinearLayout l;
    ImageView imageView;
    int ID;
    int check;
    DataBase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        imageView = (ImageView) findViewById(R.id.imageView);
        title = (EditText) findViewById(R.id.name);
        description = (EditText) findViewById(R.id.description);
        url1 = (EditText) findViewById(R.id.url);
        l = (LinearLayout) findViewById(R.id.editLayout);
        db = new DataBase(this);
        Intent A = getIntent();
        Bundle b = A.getExtras();
        if(b!=null)
        {

            String NAME =(String) b.get("name");
            String d =(String) b.get("des");
            String u =(String) b.get("url");
            int id = (int) b.get("id");
            ID=id;

            if(NAME==null){
                check=0;
                NAME=(String) b.get("title");
            }else{
                check=-1;
            }

            title.setText(NAME);
            description.setText(d);
            url1.setText(u);

        }
    }

    public void ok(View v){
        DataBase db = new DataBase(this);
        db.deleteMovie(ID);
        Intent i = new Intent(EditActivity.this, MainActivity.class);
        String s= title.getText().toString();
        String d= description.getText().toString();
        String u= url1.getText().toString();
        Movie movie = new Movie(ID, s, d, u);
        db.addMovie(movie);
        startActivity(i);
        finish();

    }
    public void cancel(View v){

        finish();
    }
    public void show(View v){

        String url = url1.getText().toString();
        new DownloadImageTask(this,l,this, imageView, url).execute();
    }
}