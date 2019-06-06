package com.example.movie;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.movie.MovieAPI.MovieRepository;
import com.example.movie.MovieDB.Movie;
import com.example.movie.View.MainActivityViewModel;
import com.example.movie.View.MovieListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	
	
	private MovieListAdapter movieListAdapter;
	private RecyclerView mRecyclerView;
	private List<Movie> movieList = new ArrayList<>();
	private ImageView posterImage;
	private Button btnMovies;
	private EditText yearEt;
	private MainActivityViewModel viewModel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initViews();
		initObservers();
		initRecyclerView();
	}
	
	private void initRecyclerView() {
		movieListAdapter = new MovieListAdapter(this, movieList);
		mRecyclerView = findViewById(R.id.rvMovieList);
		mRecyclerView.setAdapter(movieListAdapter);
		final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false);
		mRecyclerView.setLayoutManager(gridLayoutManager);
		mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(
				new ViewTreeObserver.OnGlobalLayoutListener() { // Add Global Layout Listener to calculate the span count.
					@Override
					public void onGlobalLayout() {
						mRecyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
						gridLayoutManager.setSpanCount(calculateSpanCount());
						gridLayoutManager.requestLayout();
					}
				});
		viewModel.getMovies();
	}
	
	/**
	 * Calculate the number of spans for the recycler view based on the recycler view width.
	 *
	 * @return int number of spans.
	 */
	private int calculateSpanCount() {
		int viewWidth = mRecyclerView.getMeasuredWidth();
		float cardViewWidth = getResources().getDimension(R.dimen.poster_width);
		float cardViewMargin = getResources().getDimension(R.dimen.margin_medium);
		int spanCount = (int) Math.floor(viewWidth / (cardViewWidth + cardViewMargin));
		return spanCount >= 1 ? spanCount : 1;
	}
	
	private void initViews() {
		posterImage = findViewById(R.id.posterImage);
		btnMovies = findViewById(R.id.submitBtn);
		yearEt = findViewById(R.id.yearTextInput);
		btnMovies.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				viewModel.getMovieYear(yearEt.getText().toString());
			}
		});
	}
	private void initObservers() {
		viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
		viewModel.getError().observe(this, new Observer<String>() {
			@Override
			public void onChanged(@Nullable String s) {
				showToast(s);
			}
		});
		viewModel.getMovie().observe(this, new Observer<List<Movie>>() {
			@Override
			public void onChanged(@Nullable List<Movie> movies) {
				movieList = movies;
				updateUI();
			}
		});
	}
	
	private void updateUI() {
		if (movieListAdapter == null) {
			movieListAdapter = new MovieListAdapter(this, movieList);
			mRecyclerView.setAdapter(movieListAdapter);
		} else {
			movieListAdapter.swapList(movieList);
		}
	}
	
	private void showToast(String message) {
		Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG)
				.show();
	}
}