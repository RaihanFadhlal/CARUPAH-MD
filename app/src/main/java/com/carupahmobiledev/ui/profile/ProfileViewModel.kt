package com.carupahmobiledev.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.carupahmobiledev.data.remote.body.EditBody
import com.carupahmobiledev.data.remote.response.ProfileResponse
import com.carupahmobiledev.data.repo.ProfileRepo
import com.carupahmobiledev.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepo: ProfileRepo,
) : ViewModel() {

    val profileMessage: LiveData<Event<String>>
        get() = profileRepo.profileMessage

    val profileUser: LiveData<ProfileResponse> = profileRepo.profileUser

    val isLoading: LiveData<Boolean> = profileRepo.isLoading

    fun getProfile(id :String ) = profileRepo.getProfile(id)

    fun editProfile(id :String, bodyEdit: EditBody) = profileRepo.editProfile(id, bodyEdit)
}