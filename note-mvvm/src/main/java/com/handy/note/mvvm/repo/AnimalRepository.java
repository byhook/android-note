package com.handy.note.mvvm.repo;

import androidx.lifecycle.LiveData;

import com.handy.note.mvvm.data.AnimalData;
import com.handy.note.mvvm.database.ZooAnimalDao;

import java.util.List;

/**
 * @author: handy
 * @date: 2020-05-25
 * @description:
 */
public class AnimalRepository {

    private ZooAnimalDao zooAnimDao;

    public AnimalRepository(ZooAnimalDao zooAnimDao){
        this.zooAnimDao = zooAnimDao;
    }

    public LiveData<List<AnimalData>> getAnimalData(){
        return zooAnimDao.getAnimalData();
    }

}
