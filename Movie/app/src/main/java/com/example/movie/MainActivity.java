package com.example.movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.movie.MovieAPI.MovieRepository;

public class MainActivity extends AppCompatActivity {
	
	private Button mButton;
	private EditText mEditText;
	
	private RecyclerView mRecyclerView;
	
	private MovieRepository mMovieRepository;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initButton();
		initText();
		
		
		
	}
	
	private void initButton() {
		mButton = findViewById(R.id.submit);
		mButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
	}
	
	private void initText() {
		mEditText = findViewById(R.id.editYear);
	}
	
	private void initRecyclerView() {
		mRecyclerView = findViewById(R.id.recyclerView);
	}
}
