package com.example.androidcourses.hw12

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import com.example.androidcourses.databinding.Hw12FirstTaskBinding
import java.util.concurrent.TimeUnit

class HW12FirstTaskActivity : AppCompatActivity() {
    lateinit var binding: Hw12FirstTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw12FirstTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.firstTaskBtn.setOnClickListener {
            setImage()
        }

    }

    private fun setImage() {
        Observable
            .just(IMG_1, IMG_2, IMG_3, IMG_4, IMG_5)
            .flatMap {
                Observable
                    .just(it)
            }
            .subscribe {
                Glide.with(this)
                    .load(it)
                    .into(binding.imageFirst)
            }
        Observable
            .just(IMG_1, IMG_2, IMG_3, IMG_4, IMG_5)
            .concatMap {
                Observable
                    .just(it)
                    .delay(2, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Glide.with(this)
                    .load(it)
                    .into(binding.imageSecond)
            }
        Observable
            .just(IMG_1, IMG_2, IMG_3, IMG_4, IMG_5)
            .switchMap {
                Observable
                    .just(it)
                    .delay(4, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Glide.with(this)
                    .load(it)
                    .into(binding.imageThird)
            }
    }

    companion object {
        const val IMG_1 = "https://android-obzor.com/wp-content/uploads/2022/03/2515643500.jpg"
        const val IMG_2 =
            "https://mobimg.b-cdn.net/v3/fetch/9c/9cb5a29c9a8c79597c65a71a7cfa25a9.jpeg"
        const val IMG_3 =
            "https://gas-kvas.com/uploads/posts/2023-02/1675256114_gas-kvas-com-p-kotenok-art-risunok-36.jpg"
        const val IMG_4 =
            "https://webmg.ru/wp-content/uploads/2022/05/i-130-8.jpeg"
        const val IMG_5 = "https://mobimg.b-cdn.net/v3/fetch/9f/9fc51f2883259b9f3049911a94e87148.jpeg"

    }
}