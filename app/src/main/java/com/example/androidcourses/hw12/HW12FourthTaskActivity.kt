package com.example.androidcourses.hw12

import io.reactivex.rxjava3.core.Observable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourses.databinding.Hw12FourthTaskBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class HW12FourthTaskActivity : AppCompatActivity() {
    lateinit var binding: Hw12FourthTaskBinding

    private var debounceCnt = 0
    private var throttleFirstCnt = 0
    private var throttleLastCnt = 0
    private var throttleLatestCnt = 0
    private var throttleWithTimeoutCnt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw12FourthTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val debounce = Observable.create<Int> { emitter ->
            binding.debounceBtn.setOnClickListener {
                debounceCnt++
                binding.debounceBtn.text = debounceCnt.toString()
                emitter.onNext(debounceCnt)
            }
        }.scan { it, _ -> it + 1 }.debounce(2, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.debounceTextView.text = it.toString()
            }

        val throttleFirst = Observable.create<Int> { emitter ->
            binding.throttleFirstBtn.setOnClickListener {
                throttleFirstCnt++
                binding.throttleFirstBtn.text = throttleFirstCnt.toString()
                emitter.onNext(throttleFirstCnt)
            }
        }.scan { it, _ -> it + 1 }.throttleFirst(2, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.throttleFirstTextView.text = it.toString()
            }

        val throttleLast = Observable.create<Int> { emitter ->
            binding.throttleLastBtn.setOnClickListener {
                throttleLastCnt++
                binding.throttleLastBtn.text = throttleLastCnt.toString()
                emitter.onNext(throttleLastCnt)
            }
        }.scan { it, _ -> it + 1 }.throttleLast(2, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.throttleLastTextView.text = it.toString()
            }

        val throttleLatest = Observable.create<Int> { emitter ->
            binding.throttleLatestBtn.setOnClickListener {
                throttleLatestCnt++
                binding.throttleLatestBtn.text = throttleLatestCnt.toString()
                emitter.onNext(throttleLatestCnt)
            }
        }.scan { it, _ -> it + 1 }.throttleLatest(2, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.throttleLatestTextView.text = it.toString()
            }
        val throttleWithTimeout = Observable.create<Int> { emitter ->
            binding.throttleWithTimeoutBtn.setOnClickListener {
                throttleWithTimeoutCnt++
                binding.throttleWithTimeoutBtn.text = throttleWithTimeoutCnt.toString()
                emitter.onNext(throttleWithTimeoutCnt)
            }
        }.scan { it, _ -> it + 1 }.throttleWithTimeout(2, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.throttleWithTimeoutTextView.text = it.toString()
            }
    }

}