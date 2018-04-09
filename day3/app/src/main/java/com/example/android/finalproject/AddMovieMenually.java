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


public class AddMovieMenually extends AppCompatActivity {
    private String title;
    private String description;
    private String image;
    private EditText editTextTitle;
    private EditText editTextDescription;
    private EditText editTextImage;
    private LinearLayout L;
    private ImageView imageView;




    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie_menually);
         L = (LinearLayout) findViewById(R.id.editLayout) ;
        editTextTitle  = (EditText) findViewById(R.id.titleEditText);
        editTextDescription  = (EditText) findViewById(R.id.descriptionEditText);
        editTextImage  = (EditText) findViewById(R.id.imageLinkEditText);
        imageView= (ImageView)findViewById(R.id.photoImageView) ;

          Intent i =getIntent();
          Bundle b = i.getExtras();


        if(b!=null){

            title = i.getStringExtra("MovieTitle");
            description = i.getStringExtra("MovieDescription");
            image = i.getStringExtra("MovieImage");
            setText(title,description,image);
        }

    }



    public void getText(){
        title= editTextTitle.getText().toString();
        description= editTextDescription.getText().toString();
        image= editTextImage.getText().toString();


    }


    public void setText(String title,String description, String image){
        editTextTitle.setText(title.toString());
        editTextDescription.setText(description.toString());
        editTextImage.setText(image.toString());
    }




    public void cancelButton_onClick(View view) {
        finish();
    }

    public void showButton_onClick(View view) {
        getText();

        new DownloadImageTask(this,L,this, imageView,image ).execute();

    }

    public void saveButton_onClick(View view) {
        DataBase db = new DataBase(this);

        Intent i = new Intent(AddMovieMenually.this, MainActivity.class);
        getText();
        Movie movie = new Movie(1, title, description, image);
         db.addMovie(movie);
        startActivity(i);
         finish();

        }
    }
