package com.example.androidcourses.hw11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourses.databinding.Hw11MainActivityBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject
import java.lang.Math.pow
import java.lang.Math.sqrt
import java.util.concurrent.TimeUnit

class HW11MainActivity : AppCompatActivity() {

    lateinit var binding: Hw11MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw11MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        task1()
        task2()
        task3()
        task4()
        task5()
        task6()
        task7()
        task8()
        task9()
    }

    //Измените код примера так, чтобы в результате каждый элемент представлял собой длину строки в квадрате.
    private fun task1() {
        println(TASK_1)
        Observable.just("abc", "ab", "abcd").map { pow(it.length.toDouble(), 2.0) }
            .subscribe { println(it) }
    }

    //Допишите данный оператор фильтрации так, чтобы он возвращал числа, у которых
    // извлекаемый корень принадлежит множеству целых чисел.
    private fun task2() {
        println(TASK_2)
        Observable.just(3, 4, 5).filter { sqrt(it.toDouble()) % 1 == 0.0 }.subscribe { println(it) }
    }

    //Реализуйте Observable, который оборачивает клики на кнопку.
    // Подсказка: Используйте оператор create на основе Emitter-а или Observer-а.
    private fun task3() {
        println(TASK_3)
        Observable.create { emitter ->
            emitter.setCancellable {
                binding.btn.setOnClickListener(null)
                println("false")
            }
            binding.btn.setOnClickListener { v ->
                println("button click")
                emitter.onNext(v)
            }

        }.subscribe { println("complete") }
    }

    //Дан горячий Observable, трансформируйте его в холодный:
    private fun task4() {
        println(TASK_4)
        Observable.defer {
            PublishSubject.create<Int> {
                it.onNext(1)
                it.onNext(2)
                it.onNext(3)
            }
        }.subscribe { println(it) }
    }

    //Создайте Subject с историей всех сообщений. Каждый новый подписчик должен видеть всю историю с самого начала.
    private fun task5() {
        println(TASK_5)
        val subject = ReplaySubject.create<Int>()
        subject.onNext(1)
        subject.subscribe { println("first $it") }
        subject.onNext(2)
        subject.onNext(3)
        subject.subscribe { println("second $it") }
        subject.onNext(4)
        subject.onNext(5)
    }

    //В нижеприведенном коде объявлен Observable.
    // Трансформируйте его в hot Observable и подготовьте его к отправке данных,
    // вне зависимости от того, есть ли у него подписчики или нет:
    private fun task6() {
        println(TASK_6)
        val myObservable = Observable.interval(1, TimeUnit.SECONDS)
            .publish()
        val sub1 = myObservable.subscribe { println("Observable 1 $it") }
        myObservable.connect()
        Thread.sleep(3000)
        val sub2 = myObservable.subscribe { println("Observable 2 $it") }
        Thread.sleep(3000)
        sub1.dispose()
        sub2.dispose()

    }

    //Дан Observable, трансформируйте его таким образом, чтобы каждый новый подписчик получал всю последовательность заново:
    private fun task7() {
        println(TASK_7)
        val observable = Observable.create<Int> { emitter ->
            run {
                emitter.onNext(1)
                emitter.onNext(2)
                emitter.onNext(3)
                emitter.onComplete()
            }
        }.replay()
        observable.subscribe { println(it) }
        observable.connect()
        observable.subscribe { println(it) }
    }

    //Создайте различные варианты Observable при помощи Schedulers,
    // залогируйте выполнение, чтобы посмотреть на переключение и использования потоков. (минимум 4)
    private fun task8() {
        println(TASK_8)
        Observable
            .just("item")
            .subscribeOn(Schedulers.io())
            .map { println("observable1 $it on: ${Thread.currentThread().name}") }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { println("observable1 $it on: ${Thread.currentThread().name}") }

        Observable
            .just("item")
            .subscribeOn(Schedulers.single())
            .map { println("observable2 $it on: ${Thread.currentThread().name}") }
            .observeOn(Schedulers.computation())
            .subscribe { println("observable2 $it on: ${Thread.currentThread().name}") }


        Observable
            .just("item")
            .subscribeOn(Schedulers.io())
            .map { println("observable3 $it on: ${Thread.currentThread().name}") }
            .observeOn(Schedulers.newThread())
            .subscribe { println("observable3 $it on: ${Thread.currentThread().name}") }


        Observable
            .just("item")
            .subscribeOn(Schedulers.single())
            .map { println("observable4 $it on: ${Thread.currentThread().name}") }
            .observeOn(Schedulers.trampoline())
            .subscribe { println("observable4 $it on: ${Thread.currentThread().name}") }

    }


    //Создайте Observable, генерирующий строки, который будет отфильтровывать все слова с какой-то буквой (например «р», независимо от регистра)
    // и будет их все трансформировать в верхний регистр (ТО ЕСТЬ ТАК).
    private fun task9() {
        println(TASK_9)
        Observable
            .just("hello", "hey", "orange", "light")
            .filter { it.lowercase().contains("h") }
            .map { it.uppercase() }
            .subscribe { println(it) }
    }

    companion object {
        const val TASK_1 =
            "Измените код примера так, чтобы в результате каждый элемент представлял собой длину строки в квадрате."
        const val TASK_2 =
            "Допишите данный оператор фильтрации так, чтобы он возвращал числа, у которых извлекаемый корень принадлежит множеству целых чисел."
        const val TASK_3 =
            "Реализуйте Observable, который оборачивает клики на кнопку. Подсказка: Используйте оператор create на основе Emitter-а или Observer-а."
        const val TASK_4 = "Дан горячий Observable, трансформируйте его в холодный:"
        const val TASK_5 =
            "Создайте Subject с историей всех сообщений. Каждый новый подписчик должен видеть всю историю с самого начала."
        const val TASK_6 =
            "В нижеприведенном коде объявлен Observable. Трансформируйте его в hot Observable и подготовьте его к отправке данных, вне зависимости от того, есть ли у него подписчики или нет:"
        const val TASK_7 =
            "Дан Observable, трансформируйте его таким образом, чтобы каждый новый подписчик получал всю последовательность заново:"
        const val TASK_8 =
            "Создайте различные варианты Observable при помощи Schedulers, залогируйте выполнение, чтобы посмотреть на переключение и использования потоков. (минимум 4)"
        const val TASK_9 =
            "Создайте Observable, генерирующий строки, который будет отфильтровывать все слова с какой-то буквой (например «р», независимо от регистра) и будет их все трансформировать в верхний регистр (ТО ЕСТЬ ТАК)."
    }
}