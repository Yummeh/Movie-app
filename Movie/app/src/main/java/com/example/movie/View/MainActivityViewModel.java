package com.example.movie.View;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;

import com.example.movie.MovieAPI.MovieRepository;
import com.example.movie.MovieDB.Movie;
import com.example.movie.MovieDB.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {
	
	private MovieRepository movieRepository = new MovieRepository();
	
	private MutableLiveData<Movie> movie = new MutableLiveData<>();
	private MutableLiveData<String> error = new MutableLiveData<>();
	
	public MainActivityViewModel(@NonNull Application application) {
		super(application);
	}
	
	public void getMovie() {
		movieRepository
				.getMovie()
				.enqueue(new Callback<Movie>() {
					
					@Override
					public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
						if (response.isSuccessful() && response.body() != null) {
							movie.setValue(response.body());
						} else {
							error.setValue("Api Error: " + response.message());
						}
					}
					
					@Override
					public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
						error.setValue("Api Error: " + t.getMessage());
					}
				});
	}
}
