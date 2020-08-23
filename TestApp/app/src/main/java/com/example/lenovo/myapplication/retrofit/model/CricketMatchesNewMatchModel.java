package com.example.lenovo.myapplication.retrofit.model;

import com.example.lenovo.myapplication.retrofit.ui.CricketNewMatchesContract;

import retrofit2.Call;

public class CricketMatchesNewMatchModel implements CricketNewMatchesContract.NewMatchModel {

    @Override
    public Call<NewMatchesData> getNewMatches() {

        CricketApiInterface apiService = CricketApiClient.getClient().create(CricketApiInterface.class);

        Call<NewMatchesData> call = apiService.getNewMatches(CricketApiClient.API_KEY_CRICKET);
        return call;
    }


}
