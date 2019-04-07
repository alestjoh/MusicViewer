package com.example.musicviewer;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "song_table")
public class SongItem {

    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "artist")
    @SerializedName("artistName")
    @Expose
    private String artistName;

    @ColumnInfo(name = "collection")
    @SerializedName("collectionName")
    @Expose
    private String collectionName;

    @ColumnInfo(name = "track_name")
    @SerializedName("trackName")
    @Expose
    private String trackName;

    @ColumnInfo(name = "artwork")
    @SerializedName("artworkUrl60")
    @Expose
    private String artworkUrl60;

    @ColumnInfo(name = "price")
    @SerializedName("trackPrice")
    @Expose
    private Double trackPrice;

    @ColumnInfo(name = "preview")
    @SerializedName("previewUrl")
    @Expose
    private String previewUrl;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public void setArtworkUrl60(String artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }

    public Double getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(Double trackPrice) {
        this.trackPrice = trackPrice;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }
}
