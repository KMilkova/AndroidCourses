package com.example.androidcourses.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.androidcourses.databinding.Hw5FragmentSecondTaskBinding

private const val NAME = "Name"
private const val AGE = "Age"
private const val WEIGHT = "Weight"

class HW5SecondTaskFragment : Fragment() {
    private var _binding: Hw5FragmentSecondTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Hw5FragmentSecondTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //вывод полученных данных
        arguments?.let {
            binding.nameTextView.text = it.getString(NAME)
            binding.ageTextView.text = it.getInt(AGE).toString()
            binding.weightTextView.text = it.getDouble(WEIGHT).toString()
        }

        //нажание на кнопку для передачи фрагментов из активити во второй фрагмент задание 3
        binding.buttonForThirdTask.setOnClickListener {
            val name = binding.nameTextView.text
            val age = binding.ageTextView.text.toString().toInt()
            val weight = binding.weightTextView.text.toString().toDouble()
            val checkClick = true

            setFragmentResult("requestKey", bundleOf("nameKey" to name, "ageKey" to age, "weightKey" to weight, "checkClickKey" to checkClick))

        }
    }

    companion object {
        //метод для передачи параметров во фрагмент
        @JvmStatic
        fun newInstance(name: String, age: Int, weight:Double) =
            HW5SecondTaskFragment().apply {
                arguments = Bundle().apply {
                    putString(NAME, name)
                    putInt(AGE, age)
                    putDouble(WEIGHT,weight)
                }
            }
    }
}