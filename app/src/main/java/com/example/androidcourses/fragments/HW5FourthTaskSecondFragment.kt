package com.example.androidcourses.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidcourses.databinding.Hw5SecondFragmentFourthTaskBinding

//Фрагмент для 4 задания
class HW5FourthTaskSecondFragment : Fragment() {

    private var _binding: Hw5SecondFragmentFourthTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Hw5SecondFragmentFourthTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HW5FourthTaskSecondFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}