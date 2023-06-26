package com.carupahmobiledev.ui.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carupahmobiledev.data.repo.LocationRepo

class LocViewModelFactory(
    private val locationRepo: LocationRepo
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationViewModel::class.java)) {
            return LocationViewModel(locationRepo) as T
        }
        throw IllegalArgumentException("error ${modelClass.name}")
    }
}