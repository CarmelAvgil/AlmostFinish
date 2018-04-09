package com.example.android.finalproject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
/**
 * Created by Anna on 3/12/2018.
 */

public abstract class MovieController  implements HttpRequest.Callbacks {

    protected static ArrayList<SingleMovieDetails> movies; // All countries.
    protected static ArrayList<String> movieTitles; // All countries.
    protected Activity activity; // The main activity.
    protected ProgressDialog progressDialog; // Progress dialog.
    protected ListView MovieList; // The main ListView for updating the movies list.

    // ctor:
    public MovieController(Activity activity) {
        this.activity = activity;
        MovieList = (ListView)activity.findViewById(R.id.MovieList);
        progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("Downloading...");
        progressDialog.setMessage("Please Wait...");
    }

    // Server access is about to start - show progress dialog:
    public void onAboutToStart() {
        progressDialog.show();
    }

    // Got error from server - show toast and dismiss dialog:
    public void onError(String errorMessage) {
        Toast.makeText(activity, "Error: " + errorMessage, Toast.LENGTH_LONG).show();
        progressDialog.dismiss();
    }
}