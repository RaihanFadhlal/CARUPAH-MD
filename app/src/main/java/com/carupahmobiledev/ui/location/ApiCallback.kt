package com.carupahmobiledev.ui.location

interface ApiCallback<T> {
    fun onSuccess(data: T)
    fun onError(message: String)
}