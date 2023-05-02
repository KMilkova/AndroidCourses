package com.example.androidcourses.hw10.view.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcourses.R
import com.example.androidcourses.databinding.Hw8ThirdTaskPersonInfoItemBinding
import com.example.androidcourses.hw10.model.entity.Person
import com.example.androidcourses.hw10.utils.Constants
import com.example.androidcourses.hw10.view.activities.HW10ChooseAddressActivity
import com.example.androidcourses.hw10.viewModel.HW10MainActivityViewModel

class HW10MainActivityAdapter(
    private var personList: MutableList<Person>,
    private val mainActivityViewModel: HW10MainActivityViewModel

) : RecyclerView.Adapter<HW10MainActivityAdapter.ViewHolder>() {
    class ViewHolder(
        private val binding: Hw8ThirdTaskPersonInfoItemBinding,
        private val mainActivityViewModel: HW10MainActivityViewModel
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person) {

            with(binding) {
                nameTextView.text = person.personName
                surnameTextView.text = person.personSurname
                ageTextView.text = person.personAge.toString()
                phoneTextView.text = person.personPhone.toString()
                birthdayTextView.text = person.personBirthday

                when (person.personAge) {
                    in 0..10 -> picture.setImageResource(R.drawable._527740382)
                    in 11..20 -> picture.setImageResource(R.drawable._401833208)
                    in 21..30 -> picture.setImageResource(R.drawable.pipsie)
                    in 31..45 -> picture.setImageResource(R.drawable.corgi)
                    in 46..60 -> picture.setImageResource(R.drawable._544556490)
                    in 61..70 -> picture.setImageResource(R.drawable._292959773)
                    in 71..99 -> picture.setImageResource(R.drawable._287430037)
                }

                deleteBtn.setOnClickListener {
                    mainActivityViewModel.deleteElFromDB(person)

                }

                addAddress.setOnClickListener {
                    val shared = itemView.context.getSharedPreferences(
                        Constants.SHARED_PREFERENCES_NAME,
                        Context.MODE_PRIVATE
                    )
                    shared.edit().putString(Constants.PERSON_NAME, person.personName)
                        .putString(Constants.PERSON_SURNAME, person.personSurname)
                        .putInt(Constants.PERSON_PHONE, person.personPhone)
                        .putInt(Constants.PERSON_AGE, person.personAge)
                        .putString(
                            Constants.PERSON_BIRTHDAY, person.personBirthday
                        ).putInt(Constants.PERSON_ID, person.id)
                        .apply()

                    val intent =
                        Intent(itemView.context, HW10ChooseAddressActivity::class.java)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = Hw8ThirdTaskPersonInfoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding,mainActivityViewModel)
    }

    override fun getItemCount() = personList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(personList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(persons: List<Person>){
        this.personList = persons.toMutableList()
        notifyDataSetChanged()
    }
}