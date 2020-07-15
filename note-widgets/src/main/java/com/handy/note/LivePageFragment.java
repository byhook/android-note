package com.handy.note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.handy.note.widgets.R;

/**
 * @author: handy
 * @date: 2020-07-15
 * @description:
 */
public class LivePageFragment extends Fragment {

    public static LivePageFragment newInstance() {
        Bundle args = new Bundle();
        LivePageFragment fragment = new LivePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.new_page,container,false);
        return rootView;
    }
}
