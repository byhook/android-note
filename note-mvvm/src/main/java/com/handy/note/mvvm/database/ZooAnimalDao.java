package com.handy.note.mvvm.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.handy.note.mvvm.data.AnimalData;

import java.util.List;

/**
 * @author: handy
 * @date: 2020-05-25
 * @description:
 */
@Dao
public interface ZooAnimalDao {

    @Query("SELECT * FROM animal")
    LiveData<List<AnimalData>> getAnimalData();

}
