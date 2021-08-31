package com.example.rickandmorty.presentation.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.presentation.base.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    protected val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _dataErrorResponse = SingleLiveEvent<Unit>()
    val dataErrorResponse: LiveData<Unit>
        get() = _dataErrorResponse

    fun onDataError(error: Throwable) {
        viewModelScope.launch(Dispatchers.Main) {
            _dataErrorResponse.call()
            Log.e(this::class.simpleName, error.toString())
        }
    }
}