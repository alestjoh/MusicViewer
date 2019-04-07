package com.example.musicviewer;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {SongItem.class}, version = 1, exportSchema = false)
public abstract class SongRoomDatabase extends RoomDatabase {

    public abstract SongItemDao songItemDao();

    private static volatile SongRoomDatabase instance;

    public static SongRoomDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (SongRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            SongRoomDatabase.class,
                            "song_database").build();
                }
            }
        }
        return instance;
    }

}
