package com.android.nanden.flicks.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movie {

    String posterPath;

    String originalTitle;
    String overview;
    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
    }

    /**
     * Iterate through the JSON array and convert each of the JSON object to movie object
     * @param jsonArray
     * @return ArrayList of movie object
     * @throws JSONException
     */
    public static ArrayList<Movie> fromJSONArray(JSONArray jsonArray) throws JSONException {
        ArrayList<Movie> result = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            result.add(new Movie(jsonArray.getJSONObject(i)));
        }
        return result;
    }

    // CAUTION: returned result of the posterPath is not full url.
    // also, more likely I need to adjust the size of the poster image.
    // more info: https://courses.codepath.com/courses/intro_to_android/unit/1#!hints
    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }



}
