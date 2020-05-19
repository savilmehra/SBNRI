package com.in.sbnri.roomdatabase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.in.sbnri.entities.MainEntity;


@Database(entities = {MainEntity.class}, version = 1, exportSchema = false)

public abstract class RoomDatabase extends androidx.room.RoomDatabase {
    private static RoomDatabase db;

    public abstract DaoSbnri daoSbnri();

    public static RoomDatabase getAppDatabase(Context context) {

        if (db == null) {
            synchronized (RoomDatabase.class) {
                if (db == null) {
                    db = Room.
                            databaseBuilder(context.getApplicationContext(), RoomDatabase.class, "RoomDb")
                            .fallbackToDestructiveMigration()
                            .build();
                }

            }
        }

        return db;
    }

    public static void destroyInstance() {
        db = null;
    }


}
