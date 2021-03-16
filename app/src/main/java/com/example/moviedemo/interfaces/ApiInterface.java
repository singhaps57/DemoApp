package com.example.moviedemo.interfaces;
import com.example.moviedemo.modelClass.MovieResponseModel;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("popular")
    Call<MovieResponseModel> getPopularMovie(@Query("api_key") String api_key,@Query("language") String language,@Query("page") String page);

}
