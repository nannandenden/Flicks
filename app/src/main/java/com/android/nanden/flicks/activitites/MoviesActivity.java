package com.android.nanden.flicks.activitites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.nanden.flicks.R;
import com.android.nanden.flicks.adapters.MovieArrayAdapter;
import com.android.nanden.flicks.models.Movie;
import com.android.nanden.flicks.utils.Constants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MoviesActivity extends AppCompatActivity {

    ArrayList<Movie> movies;
    MovieArrayAdapter movieArrayAdapter;
    ListView lvItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        lvItems = (ListView) findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
        // link movie ArrayList to movieArrayAdapter
        movieArrayAdapter = new MovieArrayAdapter(this, movies);
        lvItems.setAdapter(movieArrayAdapter);


        AsyncHttpClient client = new AsyncHttpClient();

        client.get(Constants.URL, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                // extract the response from the response and stores in json array
                JSONArray movieJsonResult = null;

                try {
                    movieJsonResult = response.getJSONArray("results");
                    // since we instantiate movie ArrayList already, use addAll method to add the
                    // movie objects into movie ArrayList this is a easy mistake to make
                    movies.addAll(Movie.fromJSONArray(movieJsonResult));
                    // do not forget to notify the adapter
                    movieArrayAdapter.notifyDataSetChanged();
                    Log.d("Debug", movies.toString());
                    // convert json object to movies object so we can manipulate
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
