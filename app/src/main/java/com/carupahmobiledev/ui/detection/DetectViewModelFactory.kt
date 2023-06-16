package com.carupahmobiledev.ui.detection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carupahmobiledev.data.repo.DetectRepo

class DetectViewModelFactory(
    private val detectRepo: DetectRepo
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetectViewModel::class.java)) {
            return DetectViewModel(detectRepo) as T
        }
        throw IllegalArgumentException("error ${modelClass.name}")
    }
}