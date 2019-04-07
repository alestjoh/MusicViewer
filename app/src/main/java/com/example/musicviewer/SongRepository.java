package com.example.musicviewer;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class SongRepository {
    private SongItemDao songItemDao;
    private LiveData<List<SongItem>> allSongs;

    SongRepository(Application application) {
        SongRoomDatabase db = SongRoomDatabase.getInstance(application);
        songItemDao = db.songItemDao();
        allSongs = songItemDao.getAllSongs();
    }

    LiveData<List<SongItem>> getAllSongs() {
        return allSongs;
    }

    public void insert(SongItem songItem) {
        new InsertAsync(songItemDao).execute(songItem);
    }

    private static class InsertAsync extends AsyncTask<SongItem, Void, Void> {

        SongItemDao dao;

        InsertAsync(SongItemDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(final SongItem... songItems) {
            dao.insert(songItems[0]);
            return null;
        }
    }
}
