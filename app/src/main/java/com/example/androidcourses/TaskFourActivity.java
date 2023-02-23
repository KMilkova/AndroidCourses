package com.example.androidcourses;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.androidcourses.databinding.Hw4SecondTaskBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TaskFourActivity extends AppCompatActivity {
    private Hw4SecondTaskBinding binding;
    final private String NAME = "Name";
    final private String SURNAME = "Surname";
    final private String PHONE = "Phone";
    final private String AGE = "Age";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Hw4SecondTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.nameEditText.setFilters(new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        if (source.equals("")) { // for backspace
                            return source;
                        }
                        if (source.toString().matches("[a-zA-Zа-яА-Я]+")) {
                            return source;
                        }
                        return "";
                    }

                }
        });
        binding.surnameEditText.setFilters(new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        if (source.equals("")) { // for backspace
                            return source;
                        }
                        if (source.toString().matches("[a-zA-Zа-яА-Я]+")) {
                            return source;
                        }
                        return "";
                    }
                }
        });

        List<EditText> editTextList = new ArrayList<>();
        editTextList.add(binding.nameEditText);
        editTextList.add(binding.surnameEditText);
        editTextList.add(binding.phoneEditText);
        editTextList.add(binding.ageEditText);

        for (EditText editText : editTextList) {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    for (EditText editText : editTextList) {
                        if (editText.getText().toString().equals("")) {
                            binding.button.setEnabled(false);
                        } else {
                            binding.button.setEnabled(true);

                        }
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {

                }

            });
        }

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    binding.resultTextView.setText(binding.nameEditText.getText() + ", " + binding.surnameEditText.getText() + ", " + binding.phoneEditText.getText() + ", " + binding.ageEditText.getText());
                }
            }
        });
    }
}
