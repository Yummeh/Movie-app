package com.example.movie.MovieAPI;


import com.example.movie.MovieDB.Movie;

import retrofit2.Call;

public class MovieRepository {
	
	private MovieApiService movieApiService = MovieApi.create();
	
	public Call<Movie> getMovie() {
		return movieApiService.getMovie();
	}
	
	
}
