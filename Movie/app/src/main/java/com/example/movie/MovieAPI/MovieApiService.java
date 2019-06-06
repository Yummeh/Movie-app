package com.example.movie.MovieAPI;

import com.example.movie.MovieDB.Movie;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApiService {
	@GET("/discover/movie")
	Call<Movie> getMovie();
}
