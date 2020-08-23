package com.example.lenovo.myapplication.retrofit.presenter;

import android.util.Log;

import com.example.lenovo.myapplication.retrofit.model.CricketMatchesNewMatchModel;
import com.example.lenovo.myapplication.retrofit.model.NewMatchesData;
import com.example.lenovo.myapplication.retrofit.ui.CricketNewMatchesContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CricketNewMatchesPresenter  {
    private final CricketNewMatchesContract.CricketNewMatchesView newMatchView;
    private final CricketNewMatchesContract.NewMatchModel newMatchModel;

    public CricketNewMatchesPresenter(CricketNewMatchesContract.CricketNewMatchesView newMatchView){
        this.newMatchView = newMatchView;
        newMatchModel = new CricketMatchesNewMatchModel();

    }

    public void getNewMatchesData(){
        Log.d("CricketPresenter", "getNewMatchesData");
        Call<NewMatchesData> matches = newMatchModel.getNewMatches();
        matches.enqueue(new Callback<NewMatchesData>() {
            @Override
            public void onResponse(Call<NewMatchesData> call, Response<NewMatchesData> response) {
                Log.d("Success",response.body().toString());
                if(response!=null && response.body()!=null) {
                    newMatchView.onGetNewMatchesSuccess(response.body().getMatches());
                }
            }

            @Override
            public void onFailure(Call<NewMatchesData> call, Throwable t) {
                Log.d("Failure",t.getLocalizedMessage());
                if(t != null){
                    newMatchView.onGetNewMatchesFailure(t.getLocalizedMessage());
                }
            }
        });
    }

}
