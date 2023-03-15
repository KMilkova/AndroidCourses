package com.example.androidcourses

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcourses.databinding.Hw6CardViewBinding
import com.example.androidcourses.databinding.Hw6ItemPersonBinding
import java.text.SimpleDateFormat
import java.util.*

class HW6CardAdapter(private val personList: MutableList<HW6PersonList>, val context: Context) :
    RecyclerView.Adapter<HW6CardAdapter.ViewHolder>() {

    class ViewHolder(private val binding: Hw6CardViewBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        private val formatter = SimpleDateFormat("dd.MM.yyyy")
        var res: Int = 0
        fun bind(person: HW6PersonList) {
            with(binding) {
                nameTextView.text = person.name
                surnameTextView.text = person.surname
                ageTextView.text = person.age.toString()
                phoneTextView.text = person.phone.toString()
                birthdayTextView.text = android.icu.text.SimpleDateFormat(
                    "dd.MM.yyyy",
                    Locale.getDefault()
                ).format(person.birthday)

                when (person.age) {
                    in 0..10 -> {
                        picture.setImageResource(R.drawable._527740382)
                        val bitmap2 = ContextCompat.getDrawable(context, R.drawable._527740382)
                            ?.toBitmap()
                        if (bitmap2 != null) {
                            createPalette(bitmap2, binding)
                        }
                    }
                    in 11..20 -> {
                        picture.setImageResource(R.drawable._401833208)
                        val bitmap2 = ContextCompat.getDrawable(context, R.drawable._401833208)
                            ?.toBitmap()
                        if (bitmap2 != null) {
                            createPalette(bitmap2, binding)
                        }
                    }
                    in 21..30 -> {
                        picture.setImageResource(R.drawable.pipsie)
                        val bitmap2 = ContextCompat.getDrawable(context, R.drawable.pipsie)
                            ?.toBitmap()
                        if (bitmap2 != null) {
                            createPalette(bitmap2, binding)
                        }
                    }
                    in 31..45 -> {
                        picture.setImageResource(R.drawable.corgi)
                        val bitmap2 = ContextCompat.getDrawable(context, R.drawable.corgi)
                            ?.toBitmap()
                        if (bitmap2 != null) {
                            createPalette(bitmap2, binding)
                        }
                    }
                    in 46..60 -> {
                        picture.setImageResource(R.drawable._544556490)
                        val bitmap2 = ContextCompat.getDrawable(context, R.drawable._544556490)
                            ?.toBitmap()
                        if (bitmap2 != null) {
                            createPalette(bitmap2, binding)
                        }
                    }
                    in 61..70 -> {
                        picture.setImageResource(R.drawable._292959773)
                        val bitmap2 = ContextCompat.getDrawable(context, R.drawable._292959773)
                            ?.toBitmap()
                        if (bitmap2 != null) {
                            createPalette(bitmap2, binding)
                        }
                    }
                    in 71..99 -> {
                        picture.setImageResource(R.drawable._287430037)
                        val bitmap2 = ContextCompat.getDrawable(context, R.drawable._287430037)
                            ?.toBitmap()
                        if (bitmap2 != null) {
                            createPalette(bitmap2, binding)
                        }
                    }
                }

            }
        }

        private fun createPalette(bitmap: Bitmap, binding: Hw6CardViewBinding) {
            Palette.from(bitmap).generate { palette ->
                val def = 0x000000
                binding.cardView.setBackgroundColor(palette!!.getDominantColor(def))
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HW6CardAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = Hw6CardViewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, context)
    }


    override fun onBindViewHolder(holder: HW6CardAdapter.ViewHolder, position: Int) {
        holder.bind(personList[position])
    }

    override fun getItemCount() = personList.size

}