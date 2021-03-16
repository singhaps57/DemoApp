package com.example.moviedemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.moviedemo.R;
import com.example.moviedemo.adapter.PopularMovieAdapter;
import com.example.moviedemo.modelClass.MovieResponseModel;
import com.example.moviedemo.modelClass.Results;
import com.example.moviedemo.utils.CommonMethod;
import com.example.moviedemo.utils.ConnectionDetector;
import com.example.moviedemo.viewModel.PopularMovieViewModel;

import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity {
    private RecyclerView recyclerMovie;
    private PopularMovieAdapter popularMovieAdapter;
    private PopularMovieViewModel popularMovieViewModel;
    private ConnectionDetector connectionDetector;
    private boolean isInternetPresent = false;
    private ProgressDialog progressDialog;
    private ArrayList<Results>movieArrayList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        findView();
        if (isInternetPresent) {
            getPopularMovieList();
        } else {
            CommonMethod.showToastShort(getString(R.string.internet_toast), getApplicationContext());
        }
    }

    // initialize object here...
    private void findView() {
        popularMovieViewModel = ViewModelProviders.of(this).get(PopularMovieViewModel.class);
        connectionDetector = new ConnectionDetector(getApplicationContext());
        isInternetPresent = connectionDetector.isConnectingToInternet();
        recyclerMovie = findViewById(R.id.recyclerMovie);
    }

    // popular movie adapter
    private void setMovieAdapter() {
        popularMovieAdapter = new PopularMovieAdapter(getApplicationContext(),movieArrayList);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerMovie.setLayoutManager(linearLayoutManager);
        recyclerMovie.setAdapter(popularMovieAdapter);
    }

    // api calling
    private void getPopularMovieList() {
        progressDialog = CommonMethod.showProgressDialog(progressDialog, MovieListActivity.this, getString(R.string.please_wait));
        String api_key = "4148657e9d796eb424c647bd1487ed6d";
        String language = "en-US";
        String page = "1";
        popularMovieViewModel.getPopularMovieResponseLiveData(api_key, language, page).observe(this, MovieResponseModel -> {
            CommonMethod.hideProgressDialog(progressDialog);
            if (MovieResponseModel != null) {
                movieArrayList=MovieResponseModel.getResults();
                setMovieAdapter();
            }
        });
    }
}