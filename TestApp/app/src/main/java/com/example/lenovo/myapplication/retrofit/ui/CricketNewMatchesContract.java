package com.example.lenovo.myapplication.retrofit.ui;

import com.example.lenovo.myapplication.retrofit.model.MatchData;
import com.example.lenovo.myapplication.retrofit.model.NewMatchesData;

import java.util.List;

import retrofit2.Call;

public interface CricketNewMatchesContract {

    interface NewMatchModel {
        /*interface OnFinishedListener {
            void onFinished(List<MatchData> matches);
            void onFailure(Throwable t);
        }*/
        Call<NewMatchesData> getNewMatches();
    }

    interface CricketNewMatchesView {
        void onGetNewMatchesSuccess(List<MatchData> matches);
        void onGetNewMatchesFailure(String localizedMessage);
    }
}
