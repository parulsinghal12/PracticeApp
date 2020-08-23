package com.example.lenovo.myapplication.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewMatchesData {

    @SerializedName("matches")
    @Expose
    private List<MatchData> matches = null;
    @SerializedName("v")
    @Expose
    private String v;
    @SerializedName("ttl")
    @Expose
    private Integer ttl;
    @SerializedName("provider")
    @Expose
    private CricketProvider provider;
    @SerializedName("creditsLeft")
    @Expose
    private Integer creditsLeft;

    public List<MatchData> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchData> matches) {
        this.matches = matches;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

/*    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }*/

    public Integer getCreditsLeft() {
        return creditsLeft;
    }

    public void setCreditsLeft(Integer creditsLeft) {
        this.creditsLeft = creditsLeft;
    }

}
