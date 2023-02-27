package com.example.androidcourses.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidcourses.databinding.Hw5FragmentThirdTaskBinding

private const val NAME = "Name"
private const val AGE = "Age"
private const val WEIGHT = "Weight"

//фрагмент для 3 задания
class HW5ThirdTaskFragment : Fragment() {
    private var _binding: Hw5FragmentThirdTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Hw5FragmentThirdTaskBinding.inflate(inflater, container, false)
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
    }

    companion object {
        @JvmStatic
        fun newInstance(name: String?, age: Int, weight:Double) =
            HW5ThirdTaskFragment().apply {
                arguments = Bundle().apply {
                    putString(NAME, name)
                    putInt(AGE, age)
                    putDouble(WEIGHT,weight)
                }
            }
    }
}