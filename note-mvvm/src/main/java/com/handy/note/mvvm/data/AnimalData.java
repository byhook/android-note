package com.handy.note.mvvm.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author: handy
 * @date: 2020-05-25
 * @description:
 */
@Entity(tableName = "animal")
public class AnimalData {

    @PrimaryKey
    @ColumnInfo(name = "id")
    public int animalId;

    public String animalName;

    public String animalDesc;

    public String animalImageUrl;

}
