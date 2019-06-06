package com.example.movie.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.movie.R;

public class DetailActivity extends AppCompatActivity {
	
	private ImageView backdropImage;
	private ImageView posterImage;
	private TextView title;
	private TextView releaseDate;
	private TextView rating;
	private TextView overview;
	private Button buttonBack;
	private String baseImageURL = "https://image.tmdb.org/t/p/w500";
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movie_detail);
		
		initViews();
	}
	
	private void initViews() {
		backdropImage = findViewById(R.id.backdropImage);
		posterImage = findViewById(R.id.posterImage);
		title = findViewById(R.id.title);
		releaseDate = findViewById(R.id.releaseDateText);
		rating = findViewById(R.id.ratingText);
		overview = findViewById(R.id.overviewText);
		buttonBack = findViewById(R.id.returnButton);
		
		Glide.with(this)
				.load(baseImageURL + getIntent().getStringExtra("backdropImage"))
				.centerCrop()
				.transition(new DrawableTransitionOptions().crossFade())
				.diskCacheStrategy(DiskCacheStrategy.ALL)
				.into(backdropImage);
		Glide.with(this)
				.load(baseImageURL + getIntent().getStringExtra("posterImage"))
				.centerCrop()
				.transition(new DrawableTransitionOptions().crossFade())
				.diskCacheStrategy(DiskCacheStrategy.ALL)
				.into(posterImage);
		// title.setText(getIntent().getStringExtra("title"));
		releaseDate.setText(getIntent().getStringExtra("releaseDate"));
		rating.setText(Double.toString(getIntent().getDoubleExtra("rating", 0.0)));
		overview.setText(getIntent().getStringExtra("overview"));
		buttonBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}