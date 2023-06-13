package com.carupahmobiledev.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.carupahmobiledev.data.remote.response.LoginResult
import com.carupahmobiledev.data.remote.response.RegisterResponse
import com.carupahmobiledev.data.repo.AuthRepo
import com.carupahmobiledev.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepo: AuthRepo,
) : ViewModel() {

    val regMessage: LiveData<Event<String>>
        get() = authRepo.regMessage

    val logMessage: LiveData<Event<String>>
        get() = authRepo.logMessage

    val loginUser: LiveData<LoginResult> = authRepo.loginUser
    val registerUser: LiveData<RegisterResponse> = authRepo.registerUser
    val isLoading: LiveData<Boolean> = authRepo.isLoading

    fun login(email: String, password: String) =
        authRepo.login(email, password)

    fun register(username: String, email: String, password: String, confirm_pw : String, numberPhone: String) =
        authRepo.register(username, email, password, confirm_pw, numberPhone)
}