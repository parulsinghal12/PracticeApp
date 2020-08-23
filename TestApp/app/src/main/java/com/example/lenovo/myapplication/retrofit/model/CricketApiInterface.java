package com.example.lenovo.myapplication.retrofit.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CricketApiInterface {
    //https://cricapi.com/api/matches?apikey=xtHqJY7jB6PeJzpRycNnMmPqxfp1
    @GET("matches")
    Call<NewMatchesData> getNewMatches(@Query("apikey") String apiKey);

    //https://cricapi.com/api/playerFinder?apikey=xtHqJY7jB6PeJzpRycNnMmPqxfp1&name=Tendulkar
    //@GET("playerFinder")
    //Call<PlayerFinder> getPlayerByName(@Query("apikey") String apiKey, @Query("name") String name);

}
