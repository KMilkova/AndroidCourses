package com.example.androidcourses.hw10.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcourses.databinding.Hw8ThirdTaskAddressItemBinding
import com.example.androidcourses.hw10.model.entity.Address
import com.example.androidcourses.hw10.viewModel.HW10AddressInfoViewModel

class HW10AddressInfoAdapter(
    private var addressList: List<Address>,
    private val addressInfoViewModel: HW10AddressInfoViewModel
) : RecyclerView.Adapter<HW10AddressInfoAdapter.ViewHolder>() {
    class ViewHolder(
        private val binding: Hw8ThirdTaskAddressItemBinding,
        private val addressInfoViewModel: HW10AddressInfoViewModel
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(address: Address) {
            with(binding) {
                cityTextView.text = address.city
                streetTextView.text = address.street
                houseNumberTextView.text = address.house_number
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = Hw8ThirdTaskAddressItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, addressInfoViewModel)
    }

    override fun getItemCount() = addressList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(addressList[position])
    }

}