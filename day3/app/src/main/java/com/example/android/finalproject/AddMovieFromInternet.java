 package com.example.android.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

 public class AddMovieFromInternet extends AppCompatActivity {
    private MovieReaderController mMoviesReaderController;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie_from_internet);
        mMoviesReaderController = new MovieReaderController(this);


    }

     public void goSearch_onClick(View view) {
         EditText movieName = (EditText) findViewById(R.id.movieNameSearch);
         String search = "";
         search = movieName.getText().toString();
         mMoviesReaderController.readAllMovies(search);

     }




     public void cancelSearch_onClick(View view) {
         Intent i = new Intent(AddMovieFromInternet.this, MainActivity.class);
         startActivity(i);
     }
 }