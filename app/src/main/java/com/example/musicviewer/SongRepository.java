package com.example.musicviewer;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class SongRepository {
    public static final int INSERT = 1, DELETE_ALL = 2;
    private SongItemDao songItemDao;
    private LiveData<List<SongItem>> allSongs;

    SongRepository(Application application) {
        SongRoomDatabase db = SongRoomDatabase.getDatabase(application);
        songItemDao = db.songItemDao();
        allSongs = songItemDao.getAllSongs();
    }

    LiveData<List<SongItem>> getAllSongs() {
        return allSongs;
    }

    public void insert(SongItem songItem) {
        new QueryAsync(songItemDao, INSERT).execute(songItem);
    }

    public void deleteAll() {
        new QueryAsync(songItemDao, DELETE_ALL).execute();
    }

    private static class QueryAsync extends AsyncTask<SongItem, Void, Void> {
        SongItemDao dao;
        int actionCode;

        QueryAsync(SongItemDao dao, int actionCode) {
            this.dao = dao;
            this.actionCode = actionCode;
        }

        @Override
        protected Void doInBackground(final SongItem... songItems) {
            if (actionCode == INSERT) {
                dao.insert(songItems[0]);
            } else if (actionCode == DELETE_ALL) {
                dao.deleteAll();
            }
            return null;
        }
    }
}
