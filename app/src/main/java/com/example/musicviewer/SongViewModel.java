package com.example.musicviewer;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class SongViewModel extends AndroidViewModel {
    private SongRepository repository;
    private LiveData<List<SongItem>> allSongs;

    public SongViewModel(@NonNull Application application) {
        super(application);
        repository = new SongRepository(application);
        allSongs = repository.getAllSongs();
    }

    public LiveData<List<SongItem>> getAllSongs() {
        return allSongs;
    }

    public void insert(SongItem song) {
        repository.insert(song);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
