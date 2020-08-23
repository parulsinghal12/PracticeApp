package com.example.lenovo.myapplication.retrofit.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MatchData {

    @SerializedName("unique_id")
    @Expose
    private Integer uniqueId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("dateTimeGMT")
    @Expose
    private String dateTimeGMT;
    @SerializedName("team-1")
    @Expose
    private String team1;
    @SerializedName("team-2")
    @Expose
    private String team2;
    @SerializedName("squad")
    @Expose
    private Boolean squad;
    @SerializedName("toss_winner_team")
    @Expose
    private String tossWinnerTeam;
    @SerializedName("winner_team")
    @Expose
    private String winnerTeam;
    @SerializedName("matchStarted")
    @Expose
    private Boolean matchStarted;
    @SerializedName("type")
    @Expose
    private String type;

    public Integer getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Integer uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getDate() {
        DateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:");

        try {
            Date fmt = dateFormat.parse(date);
            System.out.println(fmt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //String.
        return date.substring(0, date.length() - 14);

    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateTimeGMT() {
        return dateTimeGMT;
    }

    public void setDateTimeGMT(String dateTimeGMT) {
        this.dateTimeGMT = dateTimeGMT;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public Boolean getSquad() {
        return squad;
    }

    public void setSquad(Boolean squad) {
        this.squad = squad;
    }

    public String getTossWinnerTeam() {
        return tossWinnerTeam;
    }

    public void setTossWinnerTeam(String tossWinnerTeam) {
        this.tossWinnerTeam = tossWinnerTeam;
    }

    public String getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(String winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    public Boolean getMatchStarted() {
        return matchStarted;
    }

    public void setMatchStarted(Boolean matchStarted) {
        this.matchStarted = matchStarted;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
