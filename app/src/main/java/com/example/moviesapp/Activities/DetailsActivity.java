package com.example.moviesapp.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.moviesapp.Adapters.CategoryEachFilmListAdapter;
import com.example.moviesapp.Domain.MovieItem;
import com.example.moviesapp.R;
import com.google.gson.Gson;

public class DetailsActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ProgressBar progressBar;
    private TextView titleTxt, movieRateTxt, movieTimeTxt, movieSummaryInfo, releaseDate, MovieBudget;
    private int idFilm;
    private ImageView pic2, backImg;
    private NestedScrollView scrollView;
    private RecyclerView.Adapter adapterCategory;
    private RecyclerView recyclerViewCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        idFilm = getIntent().getIntExtra("id", 0);
        initView();
        recyclerViewCategory.setAdapter(adapterCategory);
        sendRequest();
    }

    private void sendRequest() {
        mRequestQueue = Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        mStringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/" + idFilm + "?api_key=4eea5a36d0b30b1493f4c0abae06cae4", s -> {
            Gson gson = new Gson();
            progressBar.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
            MovieItem item = gson.fromJson(s, MovieItem.class);

            Glide.with(DetailsActivity.this)
                    .load("https://image.tmdb.org/t/p/w500/" + item.getPosterPath())
                    .into(pic2);

            titleTxt.setText(item.getTitle());
            movieRateTxt.setText(String.valueOf(item.getVoteAverage()));
            movieTimeTxt.setText(String.valueOf(item.getRuntime()));
            movieSummaryInfo.setText(item.getOverview());
            MovieBudget.setText(String.valueOf("$" + item.getBudget()));
            releaseDate.setText(item.getReleaseDate());

            if(item.getGenres()!=null) {
                adapterCategory = new CategoryEachFilmListAdapter(item.getGenres());
                recyclerViewCategory.setAdapter(adapterCategory);
            }
        }, volleyError -> progressBar.setVisibility(View.GONE));
        mRequestQueue.add(mStringRequest);
    }

    private void initView() {
        titleTxt = findViewById(R.id.movieNameText);
        progressBar = findViewById(R.id.progressBarDetails);
        scrollView = findViewById(R.id.scrollView3);
        pic2 = findViewById(R.id.picDetail);
        movieRateTxt = findViewById(R.id.movieStar);
        movieTimeTxt = findViewById(R.id.movieDuration);
        movieSummaryInfo = findViewById(R.id.movieSummary);
        backImg = findViewById(R.id.backImg);
        MovieBudget = findViewById(R.id.movieBudget);
        releaseDate = findViewById(R.id.ReleaseDate);
        recyclerViewCategory = findViewById(R.id.genreView);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        backImg.setOnClickListener(v -> finish());

    }
}