package com.carupahmobiledev.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carupahmobiledev.data.TokenPreferences
import com.carupahmobiledev.data.repo.AuthRepo
import com.carupahmobiledev.ui.auth.AuthViewModel

class ViewModelFactory(
    private val tokenPref: TokenPreferences,
    private val accountRepo: AuthRepo,
    private val context: Context,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(accountRepo) as T
        }
        throw IllegalArgumentException("error ${modelClass.name}")
    }
}