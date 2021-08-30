package com.example.rickandmorty.presentation.fragment.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.GetAllCharactersInteractor
import com.example.rickandmorty.entity.Character
import com.example.rickandmorty.presentation.base.BaseViewModel
import com.example.rickandmorty.presentation.base.BaseViewModelAssistedFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersListViewModel @AssistedInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    private val getAllCharacters: GetAllCharactersInteractor
) : BaseViewModel(savedStateHandle) {
    @AssistedFactory
    interface Factory : BaseViewModelAssistedFactory<CharactersListViewModel>

    private val _showEmptyState = MutableLiveData<Boolean>()
    val showEmptyState: LiveData<Boolean>
        get() = _showEmptyState

    private val _showLoader = MutableLiveData<Boolean>()
    val showLoader: LiveData<Boolean>
        get() = _showLoader

    private val _charactersList = MutableLiveData<List<Character>>()
    val charactersList: LiveData<List<Character>>
        get() = _charactersList

    fun requestList() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchList()
        }
    }

    private suspend fun fetchList() {
        viewModelScope.launch(Dispatchers.Main) {
            _showLoader.value = true
        }

        getAllCharacters.invoke()
            .doOnSuccess { list ->
                updateList(list)
            }
            .doOnError { error -> onDataError(error) }
    }

    private fun updateList(list: List<Character>) {
        viewModelScope.launch(Dispatchers.Main) {
            _showEmptyState.value = list.isEmpty()
            _showLoader.value = false
            _charactersList.value = list
        }
    }
}