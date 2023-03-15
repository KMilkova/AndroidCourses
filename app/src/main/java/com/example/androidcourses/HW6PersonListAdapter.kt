package com.example.androidcourses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcourses.databinding.Hw6CardViewBinding
import com.example.androidcourses.databinding.Hw6ItemPersonBinding
import java.text.SimpleDateFormat
import java.util.*

class HW6PersonListAdapter(private val personList:MutableList<HW6PersonList>):RecyclerView.Adapter<HW6PersonListAdapter.ViewHolder>() {

    class ViewHolder(private val binding: Hw6ItemPersonBinding):RecyclerView.ViewHolder(binding.root){
        private val formatter = SimpleDateFormat("dd.MM.yyyy")
        lateinit var delete: Button
        fun bind(person:HW6PersonList){
            with(binding){
                nameTextView.text = person.name
                surnameTextView.text= person.surname
                ageTextView.text = person.age.toString()
                phoneTextView.text = person.phone.toString()
                birthdayTextView.text = android.icu.text.SimpleDateFormat(
                    "dd.MM.yyyy",
                    Locale.getDefault()
                ).format(person.birthday)
                delete= deleteBtn
                when(person.age){
                    in 0 .. 10 -> picture.setImageResource(R.drawable._527740382)
                    in 11 .. 20 -> picture.setImageResource(R.drawable._401833208)
                    in 21 .. 30 -> picture.setImageResource(R.drawable.pipsie)
                    in 31 .. 45 -> picture.setImageResource(R.drawable.corgi)
                    in 46 .. 60 -> picture.setImageResource(R.drawable._544556490)
                    in 61 .. 70 -> picture.setImageResource(R.drawable._292959773)
                    in 71 .. 99 -> picture.setImageResource(R.drawable._287430037)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HW6PersonListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = Hw6ItemPersonBinding.inflate(inflater,parent,false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HW6PersonListAdapter.ViewHolder, position: Int) {
        holder.bind(personList[position])
        holder.delete.setOnClickListener {
            personList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount)
        }

    }

    override fun getItemCount() = personList.size

    fun getItems():MutableList<HW6PersonList>{
        return personList
    }


    fun addItems(person: HW6PersonList){
        personList.add(person)
        notifyItemInserted(personList.lastIndex)
        notifyItemRangeChanged(personList.lastIndex, itemCount)
    }
}