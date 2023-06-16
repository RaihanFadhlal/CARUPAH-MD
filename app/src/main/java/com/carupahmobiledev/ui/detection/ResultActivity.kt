package com.carupahmobiledev.ui.detection

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.carupahmobiledev.databinding.ActivityResultBinding
import java.io.File


class ResultActivity : AppCompatActivity() {

    private lateinit var resultBinding: ActivityResultBinding
    private lateinit var detectViewModel: DetectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(resultBinding.root)

        val result = intent.getStringExtra("result")
        resultBinding.resultText.text = result

        val photo = intent.getStringExtra("photo") as File?
        val imageView = resultBinding.imageResult
        imageView.setImageURI(Uri.fromFile(photo))

    }
}