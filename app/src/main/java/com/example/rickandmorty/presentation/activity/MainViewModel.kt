package com.example.rickandmorty.presentation.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import com.example.rickandmorty.presentation.base.BaseViewModel
import com.example.rickandmorty.presentation.base.BaseViewModelAssistedFactory
import com.example.rickandmorty.presentation.base.utils.SingleLiveEvent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class MainViewModel @AssistedInject constructor(
    @Assisted savedStateHandle: SavedStateHandle
) : BaseViewModel(savedStateHandle) {
    @AssistedFactory
    interface Factory : BaseViewModelAssistedFactory<MainViewModel>

    private val _startEvent = SingleLiveEvent<Unit>()
    val startEvent: LiveData<Unit>
        get() = _startEvent

    fun onCreate() {
        _startEvent.call()
    }
}