package com.handy.note.page;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.handy.note.base.BaseNoteFragment;
import com.handy.note.mvvm.data.AnimalData;
import com.handy.note.mvvm.database.AppDataBase;
import com.handy.note.mvvm.databinding.FragmentAnimalLayerBinding;
import com.handy.note.mvvm.repo.AnimalRepository;
import com.handy.note.mvvm.viewmodel.AnimalViewModel;

import java.util.List;

/**
 * @author: handy
 * @date: 2020-05-25
 * @description:
 */
public class AnimalFragment extends BaseNoteFragment {

    private static final String TAG = "AnimalFragment";

    private FragmentAnimalLayerBinding animalLayerBinding;

    private AnimalViewModel animalViewModel = new AnimalViewModel(new AnimalRepository(AppDataBase.getInstance().getAnimalDao()));

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        animalLayerBinding = FragmentAnimalLayerBinding.inflate(inflater,container,false);
        setupViews();
        return animalLayerBinding.getRoot();
    }

    private void setupViews(){
        animalViewModel.getAnimalData().observe(this, new Observer<List<AnimalData>>() {
            @Override
            public void onChanged(List<AnimalData> animalData) {
                Log.d(TAG,"onChanged animalData=" + animalData);
            }
        });
    }

}
