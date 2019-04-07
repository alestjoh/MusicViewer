package com.example.musicviewer;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {SongItem.class}, version = 1)
public abstract class SongRoomDatabase extends RoomDatabase {

    public abstract SongItemDao songItemDao();

    private static volatile SongRoomDatabase instance;

    public static SongRoomDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (SongRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            SongRoomDatabase.class,
                            "song_database")
                            .build();
                }
            }
        }
        return instance;
    }

}
