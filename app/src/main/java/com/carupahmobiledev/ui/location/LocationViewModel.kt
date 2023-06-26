package com.carupahmobiledev.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carupahmobiledev.data.remote.response.BankSampahDetail
import com.carupahmobiledev.data.repo.LocationRepo

class LocationViewModel(private val repository: LocationRepo) : ViewModel() {

    private val _bankSampahList = MutableLiveData<List<BankSampahDetail>>()
    val bankSampahList: LiveData<List<BankSampahDetail>> = _bankSampahList

    fun getBankSampahData() {
        repository.getBankSampahList(object : ApiCallback<List<BankSampahDetail>> {
            override fun onSuccess(data: List<BankSampahDetail>) {
                _bankSampahList.value = data
            }

            override fun onError(message: String) {
                // Handle error
            }
        })
    }

}