package com.example.androidcourses.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidcourses.databinding.Hw5FirstTaskFragmentBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [HW5FirstTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HW5FirstTaskFragment : Fragment() {
    private final val TAG = "FirstFragment"


    //Задание 1 фрагмет и его жц
    private var _binding: Hw5FirstTaskFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"On create")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Hw5FirstTaskFragmentBinding.inflate(inflater, container, false)
        Log.d(TAG,"On create view")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG,"On view created")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d(TAG,"On view state restored")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"On start")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"On resume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"On pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"On stop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG,"On save instance state")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG,"On destroy view")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"On destroy")
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HW5FirstTaskFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}