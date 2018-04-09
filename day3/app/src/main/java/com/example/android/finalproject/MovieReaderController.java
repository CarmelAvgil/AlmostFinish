package com.example.android.finalproject;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

// Class for reading server data:
public class MovieReaderController extends MovieController {



    // ctor:
    public MovieReaderController(Activity activity) {super(activity);}

    // Read 20 movies by name from the server and set all the info i need:
    public void readAllMovies(String name) {
        HttpRequest httpRequest = new HttpRequest(this);
        String request = "https://api.themoviedb.org/3/search/movie?api_key=0dfa979f5f5b49d638840ce5b53339c1&query="+name+"&page=1";
        httpRequest.execute(request);
    }

    // Got all countries from the server - update all in the ListView:
    public void onSuccess(String downloadedText) {

        try {

            JSONObject jsonArray = new JSONObject(downloadedText);

            JSONArray resultArray = jsonArray.getJSONArray("results");

            // Create a new array list to hold all countries:
            movies = new ArrayList<>();
            movieTitles = new ArrayList<>();
            // Run on all JSON objects:
            for (int i = 0; i < resultArray.length(); i++) {

                JSONObject jsonObject = resultArray.getJSONObject(i);
//                int No = jsonObject.getInt("id");
                String name = jsonObject.getString("title");
                String desc = jsonObject.getString("overview");
                String image="http://image.tmdb.org/t/p/w185";
                image += jsonObject.getString("poster_path");
                SingleMovieDetails movie = new SingleMovieDetails(name,desc,image);

                // Add the country object into the countries array:
                movieTitles.add(name);
                movies.add(movie);

            }

            // Set adapter for the ListView:
            ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, movieTitles);

            // Display all:
            MovieList.setAdapter(adapter);

            //adding event listener to the ListView
            MovieList.setOnItemClickListener(
                    //On item click we will show to the screen a popup with the item's content
                    new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String selectedMovie = String.valueOf(adapterView.getItemAtPosition(i));
                            Intent intent=new Intent(activity,AddMovieMenually.class);

                            intent.putExtra("MovieTitle",movies.get(i).getTitle());
                            intent.putExtra("MovieDescription",movies.get(i).getDescription());
                            intent.putExtra("MovieImage",movies.get(i).getImage());

                            activity.startActivity(intent);
                            activity.finish();
                        }
                    }
            );
        }
        catch (JSONException ex) {
            Toast.makeText(activity, "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        // Dismiss dialog:
        progressDialog.dismiss();

    }
}