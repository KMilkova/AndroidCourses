package com.example.androidcourses.hw9

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidcourses.databinding.Hw9ItemBinding
import com.example.androidcourses.hw9.shop.Item

class HW9ItemsAdapter(private val items: List<Item>, private val context: Context) :
    RecyclerView.Adapter<HW9ItemsAdapter.ViewHolder>() {
    class ViewHolder(var binding: Hw9ItemBinding, var adapter: HW9ItemsAdapter) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            with(binding) {
                title.text = item.title
                category.text = item.category
                price.text = item.price.toString()
                rating.text = item.rating.rate.toString()

                Glide.with(adapter.context)
                    .load(item.image)
                    .centerCrop()
                    .into(binding.avatar)

                infoBtn.setOnClickListener {

                    val shared = adapter.context.getSharedPreferences(
                        HW9FirsTaskActivity.SHARED_PREFERENCES_NAME,
                        Context.MODE_PRIVATE
                    )
                    shared.edit().putInt(HW9FirsTaskActivity.ITEM_ID, item.id)
                        .apply()

                    val intent =
                        Intent(
                            adapter.context,
                            HW9SecondTaskActivity::class.java
                        ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    adapter.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = Hw9ItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, this)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}