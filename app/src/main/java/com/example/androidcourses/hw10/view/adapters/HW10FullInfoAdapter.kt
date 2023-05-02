package com.example.androidcourses.hw10.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcourses.R
import com.example.androidcourses.databinding.Hw8ThirdTaskFullInfoItemBinding
import com.example.androidcourses.hw10.model.entity.Address
import com.example.androidcourses.hw10.model.entity.Person
import com.example.androidcourses.hw10.viewModel.HW10FullInfoViewModel

class HW10FullInfoAdapter(
    private val personList: MutableList<Person>,
    private val fullInfoViewModel: HW10FullInfoViewModel,
    private val context: LifecycleOwner
) : RecyclerView.Adapter<HW10FullInfoAdapter.ViewHolder>() {
    class ViewHolder(
        private val binding: Hw8ThirdTaskFullInfoItemBinding,
        private val fullInfoViewModel: HW10FullInfoViewModel,
        private val context: LifecycleOwner
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(person: Person) {
            with(binding) {
                nameTextView.text = person.id.toString()
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

                var address:Address? = null
                fullInfoViewModel.addressLiveData.observe(context){ addressList ->
                    addressList.forEach {
                        if(it.id == fullInfoViewModel.getAddressId(person.id)){
                           address = it
                        }
                    }
                }

                cityTextView.text = address?.city
                streetTextView.text = address?.street

                houseNumberTextView.text = address?.house_number

//                val address = fullInfoViewModel.getAddressForPerson(person.id)
//                cityTextView.text = fullInfoViewModel.getAddressForPerson(person.id).toString()
//                streetTextView.text = fullInfoViewModel.isPersonHasAddress(person.id).toString()
//
//                houseNumberTextView.text = person.id.toString()

//                if (fullInfoViewModel.getAddressForPerson(person.id)) {
//                    cityTextView.text = address.city
//                    streetTextView.text = address.street
//                    houseNumberTextView.text = address.house_number

//                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = Hw8ThirdTaskFullInfoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, fullInfoViewModel, context)
    }

    override fun getItemCount() = personList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(personList[position])
    }
}

