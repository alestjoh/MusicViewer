package com.example.musicviewer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MusicList {
    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("results")
    @Expose
    private List<SongItem> results = null;

    public Integer getResultCount() {
        return resultCount;
    }

    public List<SongItem> getResults() {
        return results;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public void setResults(List<SongItem> results) {
        this.results = results;
    }
}
