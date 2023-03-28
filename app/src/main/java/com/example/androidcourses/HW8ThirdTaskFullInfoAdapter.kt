package com.example.androidcourses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcourses.databinding.Hw8ThirdTaskFullInfoItemBinding
import com.example.androidcourses.room.AppDatabase
import com.example.androidcourses.room.entity.Person
import kotlinx.coroutines.*

class HW8ThirdTaskFullInfoAdapter(
    private val personList: MutableList<Person>,
    private val db: AppDatabase
) : RecyclerView.Adapter<HW8ThirdTaskFullInfoAdapter.ViewHolder>() {
    class ViewHolder(
        private val binding: Hw8ThirdTaskFullInfoItemBinding,
        private val db: AppDatabase
    ) :
        RecyclerView.ViewHolder(binding.root) {

        @OptIn(DelicateCoroutinesApi::class)
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

                val addressDao = db.addressDao()
                val personAddressDao = db.personAddressDao()
                GlobalScope.launch(Dispatchers.IO) {
                    if (personAddressDao.getCountOfAddressForPerson(person.id) != 0) {
                        val address = addressDao.getAddressByPersonId(person.id)

                        withContext(Dispatchers.Main) {
                            cityTextView.text = address.city
                            streetTextView.text = address.street
                            houseNumberTextView.text = address.house_number
                        }


                    }

                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = Hw8ThirdTaskFullInfoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, db)
    }

    override fun getItemCount() = personList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(personList[position])
    }
}