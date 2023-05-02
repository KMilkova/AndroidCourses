package com.example.androidcourses.hw10.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidcourses.databinding.Hw8ThirdTaskAddressActivityBinding
import com.example.androidcourses.hw10.view.adapters.HW10AddressInfoAdapter
import com.example.androidcourses.hw10.viewModel.HW10AddressInfoViewModel

class HW10AddressInfoActivity : AppCompatActivity() {
    lateinit var binding: Hw8ThirdTaskAddressActivityBinding
    private lateinit var addressInfoViewModel: HW10AddressInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw8ThirdTaskAddressActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addressInfoViewModel =
            ViewModelProvider(this)[HW10AddressInfoViewModel::class.java]

        addressInfoViewModel.addressList.observe(this) {
            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.addressList.layoutManager = layoutManager
            val adapter = HW10AddressInfoAdapter(it, addressInfoViewModel)
            binding.addressList.adapter = adapter
        }

    }


}