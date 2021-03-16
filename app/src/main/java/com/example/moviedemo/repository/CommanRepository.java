package com.example.moviedemo.repository;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.moviedemo.interfaces.ApiInterface;
import com.example.moviedemo.modelClass.MovieResponseModel;
import com.example.moviedemo.networkClass.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommanRepository {
    private static final String TAG = CommanRepository.class.getSimpleName();
    private ApiInterface apiRequest;

    public CommanRepository() {
        apiRequest = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
    }

    // login api
    public LiveData<MovieResponseModel> getPopularMovies(String api_key,String language,String page) {
        final MutableLiveData<MovieResponseModel> data = new MutableLiveData<>();
        apiRequest.getPopularMovie(api_key,language,page)
                .enqueue(new Callback<MovieResponseModel>() {
                    @Override
                    public void onResponse(Call<MovieResponseModel> call, Response<MovieResponseModel> response) {
                        Log.d(TAG, "Popular Movie response:: " + response);
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponseModel> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }


}
