package com.example.androidcourses

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcourses.HW8SecondTaskActivity.Companion.PERSON_AGE
import com.example.androidcourses.HW8SecondTaskActivity.Companion.PERSON_BIRTHDAY
import com.example.androidcourses.HW8SecondTaskActivity.Companion.PERSON_ID
import com.example.androidcourses.HW8SecondTaskActivity.Companion.PERSON_NAME
import com.example.androidcourses.HW8SecondTaskActivity.Companion.PERSON_PHONE
import com.example.androidcourses.HW8SecondTaskActivity.Companion.PERSON_SURNAME
import com.example.androidcourses.HW8SecondTaskActivity.Companion.SHARED_PREFERENCES_NAME
import com.example.androidcourses.databinding.Hw8ThirdTaskPersonInfoItemBinding
import com.example.androidcourses.room.AppDatabase
import com.example.androidcourses.room.entity.Person
import java.util.*
import java.util.concurrent.Executors

@RequiresApi(Build.VERSION_CODES.N)
class HW8ThirdTaskPersonInfoAdapter(
    private val personList: MutableList<Person>,
    private val db: AppDatabase,
    private val context: Context
) : RecyclerView.Adapter<HW8ThirdTaskPersonInfoAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: Hw8ThirdTaskPersonInfoItemBinding,
        private val db: AppDatabase,
        private val adapter: HW8ThirdTaskPersonInfoAdapter
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
                    adapter.deleteItem(adapterPosition)
                    Executors.newSingleThreadExecutor().execute {
                        db.personDao().deletePerson(person)
                    }
                }

                addAddress.setOnClickListener {
                    val shared = adapter.context.getSharedPreferences(
                        SHARED_PREFERENCES_NAME,
                        Context.MODE_PRIVATE
                    )
                    shared.edit().putString(PERSON_NAME, person.personName)
                        .putString(PERSON_SURNAME, person.personSurname)
                        .putInt(PERSON_PHONE, person.personPhone)
                        .putInt(PERSON_AGE, person.personAge)
                        .putString(
                            PERSON_BIRTHDAY, person.personBirthday
                        ).putInt(PERSON_ID, person.id)
                        .apply()

                    val intent =
                        Intent(adapter.context, HW8ThirdTaskChooseAddressActivity::class.java)
                    adapter.context.startActivity(intent)
                }
            }
        }
    }

    fun deleteItem(position: Int) {
        personList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = Hw8ThirdTaskPersonInfoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, db, this)
    }

    override fun getItemCount() = personList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(personList[position])
    }


}