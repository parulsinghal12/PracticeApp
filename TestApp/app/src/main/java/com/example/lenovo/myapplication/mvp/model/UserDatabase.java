package com.example.lenovo.myapplication.mvp.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    public static final String DB_NAME = "user_db";

    public abstract Userdao getUserDAO(); // TODO : doubt

    private static UserDatabase INSTANCE;

    public static UserDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, DB_NAME)
                            .build();
        }
        return INSTANCE;
    }
    /*
    * public abstract MovieDao movieDao();

    private static MovieDatabase INSTANCE;

    public static MovieDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), MovieDatabase.class, "movie_db")
                            .build();
        }
        return INSTANCE;
    }*/

}
