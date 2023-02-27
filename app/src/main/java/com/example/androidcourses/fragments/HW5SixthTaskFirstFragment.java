package com.example.androidcourses.fragments;

import static androidx.core.os.BundleKt.bundleOf;
import static androidx.fragment.app.FragmentKt.setFragmentResult;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidcourses.databinding.Hw5FirstFragmentSixthTaskBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HW5SixthTaskFirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HW5SixthTaskFirstFragment extends Fragment {
    public interface OnFragmentSendDataListener {
        void onSendData(String data);
    }

    private OnFragmentSendDataListener fragmentSendDataListener;
    private static final String NAME = "name";
    private String name;
    private Hw5FirstFragmentSixthTaskBinding binding = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentSendDataListener = (OnFragmentSendDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }


    public HW5SixthTaskFirstFragment() {
    }


    public static HW5SixthTaskFirstFragment newInstance(String name) {
        HW5SixthTaskFirstFragment fragment = new HW5SixthTaskFirstFragment();
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
        binding = Hw5FirstFragmentSixthTaskBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(NAME);
        }

        //передача данных из первого фрагмента
        binding.buttonForTaskSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle result = new Bundle();
                result.putString("bundleKey", "result");
                getParentFragmentManager().setFragmentResult("requestKey", result);
                fragmentSendDataListener.onSendData(binding.nameEditText.getText().toString());
            }
        });


    }
}