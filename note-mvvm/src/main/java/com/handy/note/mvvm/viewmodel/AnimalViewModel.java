package com.handy.note.mvvm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.handy.note.mvvm.data.AnimalData;
import com.handy.note.mvvm.repo.AnimalRepository;

import java.util.List;

/**
 * @author: handy
 * @date: 2020-05-25
 * @description:
 */
public class AnimalViewModel extends ViewModel {

    private AnimalRepository animalRepository;

    public AnimalViewModel(AnimalRepository animalRepository){
        this.animalRepository = animalRepository;
    }

    public LiveData<List<AnimalData>> getAnimalData(){
        return animalRepository.getAnimalData();
    }

}
