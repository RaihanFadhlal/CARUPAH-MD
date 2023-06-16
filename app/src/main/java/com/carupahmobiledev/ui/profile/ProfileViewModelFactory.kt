package com.carupahmobiledev.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carupahmobiledev.data.repo.ProfileRepo

class ProfileViewModelFactory(
    private val profileRepo: ProfileRepo
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(profileRepo) as T
        }
        throw IllegalArgumentException("error ${modelClass.name}")
    }
}