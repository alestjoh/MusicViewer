package com.example.musicviewer;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SongItemDao {
    @Insert
    void insert(SongItem songItem);

    @Query("SELECT * FROM song_table ORDER BY uid ASC")
    LiveData<List<SongItem>> getAllSongs();

    @Query("DELETE FROM song_table")
    void deleteAll();
}
