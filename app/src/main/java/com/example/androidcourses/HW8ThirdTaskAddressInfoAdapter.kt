package com.example.androidcourses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcourses.databinding.Hw8ThirdTaskAddressItemBinding
import com.example.androidcourses.room.entity.Address

class HW8ThirdTaskAddressInfoAdapter(
    private val addressList: MutableList<Address>
) : RecyclerView.Adapter<HW8ThirdTaskAddressInfoAdapter.ViewHolder>() {
    class ViewHolder(
        private val binding: Hw8ThirdTaskAddressItemBinding
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
        return ViewHolder(binding)
    }

    override fun getItemCount() = addressList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(addressList[position])
    }
}