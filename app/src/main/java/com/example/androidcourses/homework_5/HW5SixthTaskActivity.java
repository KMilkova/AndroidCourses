package com.example.androidcourses.homework_5;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentKt;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import com.example.androidcourses.R;
import com.example.androidcourses.databinding.Hw5SixthTaskBinding;
import com.example.androidcourses.fragments.HW5SixthTaskFirstFragment;
import com.example.androidcourses.fragments.HW5SixthTaskSecondFragment;

public class HW5SixthTaskActivity extends AppCompatActivity implements HW5SixthTaskFirstFragment.OnFragmentSendDataListener {
    private static final String KEY_RESULT = "Key";
    private Hw5SixthTaskBinding binding;


    //способ передачи данных между фрагментами с помощью интерфейса, 
    //в 3 задании была отправка данных через FragmentManager
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Hw5SixthTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public void onSendData(String name) {
        HW5SixthTaskSecondFragment fragment = (HW5SixthTaskSecondFragment) getSupportFragmentManager()
                .findFragmentById(R.id.secondFragment);
        if (fragment != null)
            fragment.setNameText(name);
    }
}
