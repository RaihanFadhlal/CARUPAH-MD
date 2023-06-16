package com.carupahmobiledev.ui.detection

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.carupahmobiledev.R
import com.carupahmobiledev.databinding.FragmentDetectBinding
import com.carupahmobiledev.databinding.FragmentResultBinding
import java.io.File

class ResultFragment : Fragment() {

    private lateinit var resultBinding: FragmentResultBinding
    private lateinit var detectViewModel: DetectViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        resultBinding = FragmentResultBinding.inflate(inflater, container, false)
        return resultBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun displayImage(file: File?) {
        if (file != null) {
            resultBinding.imageResult.setImageBitmap(BitmapFactory.decodeFile(file.path))
        }
    }

}