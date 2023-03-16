package com.example.androidcourses.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidcourses.R;
import com.example.androidcourses.databinding.Hw5SecondFragmentSixthTaskBinding;

public class HW5SixthTaskSecondFragment extends Fragment {
    private static final String NAME = "Name";
    private Hw5SecondFragmentSixthTaskBinding binding;
    private String name;
    private static final String KEY_RESULT = "Key";

    public HW5SixthTaskSecondFragment() {
    }

    public static HW5SixthTaskSecondFragment newInstance(String name) {
        HW5SixthTaskSecondFragment fragment = new HW5SixthTaskSecondFragment();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = Hw5SecondFragmentSixthTaskBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(NAME);
        }
        if(savedInstanceState!=null){
            binding.resultTextView.setText(savedInstanceState.getString(KEY_RESULT));
        }
    }

    public void setNameText(String nameText) {
        binding.resultTextView.setText(nameText);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_RESULT, binding.resultTextView.getText().toString());
    }
}