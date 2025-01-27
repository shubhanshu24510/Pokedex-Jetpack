package com.shubhans.core.presentation.viewmodel

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    protected val key: ViewModelKey = ViewModelKey(this::class.java.name)

    fun <T> BaseViewModel.viewModelStateFlow(value: T): ViewModelStateFlow<T> {
        return ViewModelStateFlow(key, value)
    }
}