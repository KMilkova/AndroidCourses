package com.example.androidcourses

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcourses.HW6FirstTaskActivity.Companion.DATE_FORMAT
import com.example.androidcourses.HW8SecondTaskActivity.Companion.PERSON_AGE
import com.example.androidcourses.HW8SecondTaskActivity.Companion.PERSON_BIRTHDAY
import com.example.androidcourses.HW8SecondTaskActivity.Companion.PERSON_NAME
import com.example.androidcourses.HW8SecondTaskActivity.Companion.PERSON_PHONE
import com.example.androidcourses.HW8SecondTaskActivity.Companion.PERSON_SURNAME
import com.example.androidcourses.HW8SecondTaskActivity.Companion.SHARED_PREFERENCES_NAME
import com.example.androidcourses.databinding.Hw6ItemPersonBinding
import java.util.*

@RequiresApi(Build.VERSION_CODES.N)
class HW6PersonListAdapter(
    private val personList: MutableList<HW6PersonList>,
    private val context: Context
) : RecyclerView.Adapter<HW6PersonListAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: Hw6ItemPersonBinding,
        private val adapter: HW6PersonListAdapter
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(person: HW6PersonList) {
            with(binding) {
                nameTextView.text = person.name
                surnameTextView.text = person.surname
                ageTextView.text = person.age.toString()
                phoneTextView.text = person.phone.toString()
                birthdayTextView.text = android.icu.text.SimpleDateFormat(
                    DATE_FORMAT,
                    Locale.getDefault()
                ).format(person.birthday)
                binding.deleteBtn.setOnClickListener {
                    adapter.deleteItem(adapterPosition)
                    adapter.updateDatabase(person.id)
                }

                binding.showInfoAboutElement.setOnClickListener {
                    val shared = adapter.context.getSharedPreferences(
                        SHARED_PREFERENCES_NAME,
                        Context.MODE_PRIVATE
                    )
                    shared.edit().putString(PERSON_NAME, person.name)
                        .putString(PERSON_SURNAME, person.surname)
                        .putInt(PERSON_PHONE, person.phone)
                        .putInt(PERSON_AGE, person.age)
                        .putString(
                            PERSON_BIRTHDAY, android.icu.text.SimpleDateFormat(
                                DATE_FORMAT,
                                Locale.getDefault()
                            ).format(person.birthday)
                        ).apply()

                    val intent = Intent(adapter.context, HW8SecondTaskActivity::class.java)
                    adapter.context.startActivity(intent)
                }

                when (person.age) {
                    in 0..10 -> picture.setImageResource(R.drawable._527740382)
                    in 11..20 -> picture.setImageResource(R.drawable._401833208)
                    in 21..30 -> picture.setImageResource(R.drawable.pipsie)
                    in 31..45 -> picture.setImageResource(R.drawable.corgi)
                    in 46..60 -> picture.setImageResource(R.drawable._544556490)
                    in 61..70 -> picture.setImageResource(R.drawable._292959773)
                    in 71..99 -> picture.setImageResource(R.drawable._287430037)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = Hw6ItemPersonBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, this)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(personList[position])
    }

    fun deleteItem(position: Int) {
        personList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    fun updateDatabase(id: Int) {
        val database = HW8DatabaseHelper(context)
        val sqlDb = database.readableDatabase
        sqlDb.delete(
            HW8DatabaseHelper.TABLE_NAME,
            HW8DatabaseHelper.COLUMN_ID + "=" + "?",
            arrayOf("$id")
        )

    }

    fun clearInfo(list: MutableList<HW6PersonList>) {
        list.clear()
    }

    override fun getItemCount() = personList.size


    fun addItems(person: HW6PersonList) {
        personList.add(person)
        notifyItemInserted(personList.lastIndex)
        notifyItemRangeChanged(personList.lastIndex, itemCount)
    }
}