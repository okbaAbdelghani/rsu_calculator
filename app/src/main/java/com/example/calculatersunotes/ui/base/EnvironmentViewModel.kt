package com.example.calculatersunotes.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EnvironmentViewModel : ViewModel() {
    private var _environment : MutableLiveData<String> = MutableLiveData("")
    val environment : LiveData<String> = _environment

    fun updateEnvironment(value: String) {
        _environment.value = value
    }

}