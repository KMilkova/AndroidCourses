package com.example.androidcourses

import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidcourses.HW6FirstTaskActivity.Companion.PERSON_LIST
import com.example.androidcourses.databinding.Hw6SecondTaskBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.File
import java.io.FileOutputStream


class HW6SecondActivity : AppCompatActivity() {
    private lateinit var binding: Hw6SecondTaskBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw6SecondTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val personList: MutableList<HW6PersonList> =
            intent.getSerializableExtra(PERSON_LIST) as MutableList<HW6PersonList>
        val personListAdapter = HW6CardAdapter(personList, applicationContext)
        binding.recViewForCard.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recViewForCard.adapter = personListAdapter
        binding.linearLayoutRadioButton.isChecked = true
        var isLinear = true
        val radioGrp: RadioGroup = binding.layoutTypeRadioGroup
        radioGrp.setOnCheckedChangeListener { _, checkedId ->
            isLinear = checkedId == binding.linearLayoutRadioButton.id
            val layoutManager = if (!isLinear) {
                GridLayoutManager(this, 2)
            } else {
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            }
            binding.recViewForCard.layoutManager = layoutManager
            binding.recViewForCard.adapter = personListAdapter
        }

        binding.menuView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.complimentItem -> openAlertDialog()
                R.id.instaItem -> {
                    val uri: Uri = Uri.parse("http://instagram.com/n_milkovaa")
                    val likeIng = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(likeIng)
                }
                R.id.youtubeItem -> {
                    val uri: Uri = Uri.parse("https://www.youtube.com/shorts/EsjbelGLtao")
                    val youtube = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(youtube)
                }
                R.id.additionalTaskActivity -> {
                    val intent = Intent(applicationContext, AdditionalTaskActivity::class.java)
                    startActivity(intent)
                }
                R.id.savePicturesInInternalStorage -> {
                    savePicturesInInternalStorage()
                }
                R.id.savePicturesInExternalStorage -> {
                    savePicturesInExternalStorage()
                }
            }
            true
        }
    }

    private fun savePicturesInInternalStorage() {
        val bitmap = ContextCompat.getDrawable(applicationContext, R.drawable._544556490)
            ?.toBitmap()
        val dir = applicationContext.filesDir
        val file = File(dir, PICTURE_FILE_NAME)
        val outStream = FileOutputStream(file)
        bitmap?.compress(Bitmap.CompressFormat.PNG, 90, outStream)
        outStream.close()
    }

    private fun savePicturesInExternalStorage() {
        if (Build.VERSION.SDK_INT >= 29) {
            val contentValues = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, "picture")
                put(MediaStore.Images.Media.DESCRIPTION, "picture")
                put(MediaStore.Images.Media.MIME_TYPE, "image/png")
                put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis())
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/AndroidCourses")
            }

            try{
            val contentResolver = contentResolver
            val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

            val outputStream = contentResolver.openOutputStream(uri!!)
            val bitmap = ContextCompat.getDrawable(applicationContext, R.drawable._544556490)
                ?.toBitmap()
            bitmap?.compress(Bitmap.CompressFormat.PNG, 90, outputStream)
            outputStream?.close()}catch (e:java.lang.Exception){
                println(e.message)
            }
        } else {
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
            } else {
                val bitmap = ContextCompat.getDrawable(applicationContext, R.drawable._544556490)
                    ?.toBitmap()
                MediaStore.Images.Media.insertImage(
                    contentResolver,
                    bitmap,
                    PICTURE_TITLE,
                    PICTURE_DESCRIPTION
                )
            }

        }
    }

    private fun openAlertDialog() {
        MaterialAlertDialogBuilder(this, R.style.CutShapeAppearance)
            .setTitle(DIALOG_TITLE)
            .setMessage(DIALOG_MESSAGE)
            .setPositiveButton(POSITIVE_ANSWER) { _, _ -> }
            .show()
    }

    companion object{
        const val PICTURE_FILE_NAME = "picture.jpg"
        const val DIALOG_TITLE = "Compliment"
        const val DIALOG_MESSAGE = "You are pretty"
        const val POSITIVE_ANSWER = "YES"
        const val PICTURE_TITLE = "Picture.jpg"
        const val PICTURE_DESCRIPTION = "picture"
    }
}