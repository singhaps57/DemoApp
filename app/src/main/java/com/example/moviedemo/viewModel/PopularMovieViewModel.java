package com.example.moviedemo.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moviedemo.modelClass.MovieResponseModel;
import com.example.moviedemo.repository.CommanRepository;

import java.util.HashMap;

//
public class PopularMovieViewModel extends AndroidViewModel {
    private CommanRepository commanRepository;
    private LiveData<MovieResponseModel> movieResponseLiveData;

    public PopularMovieViewModel(@NonNull Application application) {
        super(application);
        commanRepository = new CommanRepository();
    }

    public LiveData<MovieResponseModel> getPopularMovieResponseLiveData(String api_key,String language,String page) {
        return movieResponseLiveData = commanRepository.getPopularMovies(api_key,language,page);
    }

}
