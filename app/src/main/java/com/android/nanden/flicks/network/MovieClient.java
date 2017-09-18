package com.android.nanden.flicks.network;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MovieClient {
    private final String API_KEY = "?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    private final String API_BASE_URL = "https://api.themoviedb.org/3/movie/now_playing";
    private AsyncHttpClient client;

    public MovieClient() {
        this.client = new AsyncHttpClient();
    }

    private String getNowPlayingUrl() {
        return API_BASE_URL + API_KEY;
    }

    public void getMovies(JsonHttpResponseHandler handler) {
        String url = getNowPlayingUrl();
        client.get(url, handler);
    }

}
