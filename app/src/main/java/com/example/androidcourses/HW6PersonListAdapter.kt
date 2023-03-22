package com.example.androidcourses

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcourses.HW6FirstTaskActivity.Companion.DATE_FORMAT
import com.example.androidcourses.HW6FirstTaskActivity.Companion.FILE_NAME
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
                    adapter.updateFile()
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

    fun clearInfo(list: MutableList<HW6PersonList>) {
        list.clear()
    }

    fun deleteItem(position: Int) {
        personList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    private fun clearFile() {
        context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
            it.write("".toByteArray())
        }
    }

    fun updateFile() {
        clearFile()

        personList.forEach { _ ->
            context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
                it.write(
                    replace(personList.toString()).toByteArray()
                )
            }
        }

    }

    private fun replace(info: String): String {
        return info.replace("[", "").replace("]", "").replace(", ", "")
    }

    override fun getItemCount() = personList.size

    fun getItems(): MutableList<HW6PersonList> {
        return personList
    }


    fun addItems(person: HW6PersonList) {
        personList.add(person)
        notifyItemInserted(personList.lastIndex)
        notifyItemRangeChanged(personList.lastIndex, itemCount)
    }
}