package com.carupahmobiledev.ui.detection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carupahmobiledev.data.remote.body.EditBody
import com.carupahmobiledev.data.remote.response.DetectResponse
import com.carupahmobiledev.data.remote.response.ProfileResponse
import com.carupahmobiledev.data.repo.DetectRepo
import com.carupahmobiledev.data.repo.ProfileRepo
import com.carupahmobiledev.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class DetectViewModel @Inject constructor(
    private val detectRepo: DetectRepo,
) : ViewModel() {

    val detMessage: LiveData<Event<String>>
        get() = detectRepo.detMessage

    val detectImage: LiveData<DetectResponse> = detectRepo.detectImage

    val isLoading: LiveData<Boolean> = detectRepo.isLoading

    fun detectImg(image: MultipartBody.Part) = detectRepo.detectImg(image)
}