package com.handy.note.mvvm.database;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.handy.note.core.AppCore;
import com.handy.note.mvvm.data.AnimalData;

import java.util.List;

/**
 * @author: handy
 * @date: 2020-05-25
 * @description:
 */
public abstract class AppDataBase extends RoomDatabase {

    private static final String APP_DB_NAME = "android-note";

    private static AppDataBase sInstance;

    public static AppDataBase getInstance() {
        if (sInstance == null) {
            synchronized (AppDataBase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(AppCore.getContext(),AppDataBase.class,APP_DB_NAME)
                            .build();;
                }
            }
        }
        return sInstance;
    }

    public ZooAnimalDao getAnimalDao() {
        return null;
    }
}
