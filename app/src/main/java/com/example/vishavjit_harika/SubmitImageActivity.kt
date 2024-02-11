package com.example.vishavjit_harika

import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.vishavjit_harika.ui.home.ImageGrids
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SubmitImageActivity : AppCompatActivity() {

    private var stressLevel: Int = 0
    private val FILE_HEADER = "Time, Stress"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit_image)

        supportActionBar?.title = "StressMeter"

        val stressLevelIntent = intent
        val imagePosition = stressLevelIntent.getStringExtra("position")
        val gridNumber = stressLevelIntent.getStringExtra("grid_number")

        val chosenImageView = findViewById<ImageView>(R.id.selected_imageView)
        chosenImageView.setImageResource(ImageGrids.getGridById(gridNumber?.toInt() ?: 0)!![imagePosition?.toInt() ?: 0])

        stressLevel = when (imagePosition?.toInt()) {
            0 -> 6
            1 -> 8
            2 -> 14
            3 -> 16
            4 -> 5
            5 -> 7
            6 -> 13
            7 -> 15
            8 -> 2
            9 -> 4
            10 -> 10
            11 -> 12
            12 -> 1
            13 -> 3
            14 -> 9
            15 -> 11
            else -> 0
        }

        val cancelButton: Button = findViewById(R.id.cancel_button)
        cancelButton.setOnClickListener {
            finish()
        }

        val submitButton: Button = findViewById(R.id.submit_button)
        submitButton.setOnClickListener {
            writeToCsv(stressLevel)
            finish()
            moveTaskToBack(true)
        }
    }

    private fun writeToCsv(stressValue : Int){
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val current = LocalDateTime.now().format(formatter)

        val fileName = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/stress_timestamp.csv"
        val file = File(fileName)
        if (!file.exists() || file.isDirectory) {
            file.createNewFile()
            file.appendText(FILE_HEADER)
        }
        val line = "\n$current, $stressValue"
        file.appendText(line)
    }
}