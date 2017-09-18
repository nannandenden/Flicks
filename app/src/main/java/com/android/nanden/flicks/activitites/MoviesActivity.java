package com.android.nanden.flicks.activitites;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.nanden.flicks.R;
import com.android.nanden.flicks.adapters.MovieAdapter;
import com.android.nanden.flicks.models.Movie;
import com.android.nanden.flicks.network.MovieClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MoviesActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private MovieAdapter movieAdapter;
    private MovieClient client;
    // activity view lookup
    @BindView(R.id.rvMovies) RecyclerView recyclerView;
    @BindView(R.id.swipeContainer) SwipeRefreshLayout swipeContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        movies = new ArrayList<>();
        // link movie ArrayList to movieArrayAdapter
        movieAdapter = new MovieAdapter(this, movies);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchMovies();
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchMovies();
            }
        });
    }

    private void fetchMovies() {
        client = new MovieClient();
        client.getMovies(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                JSONArray movieJsonResult = null;

                try {
                    movieJsonResult = response.getJSONArray("results");

                    movies.addAll(Movie.fromJSONArray(movieJsonResult));
                    movieAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    Log.d("LOG_TAG", "ERROR: " + e.getMessage());
                }
                if (swipeContainer.isRefreshing()) {
                    swipeContainer.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("LOG_TAG", "Request failed. status code: " + statusCode + "\t" +
                        responseString);
            }
        });
    }
}
