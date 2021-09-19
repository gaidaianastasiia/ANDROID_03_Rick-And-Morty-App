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

    private val _showRefreshing = MutableLiveData<Boolean>()
    val showRefreshing: LiveData<Boolean>
    get() = _showRefreshing

    private val _charactersList = MutableLiveData<List<Character>>()
    val charactersList: LiveData<List<Character>>
        get() = _charactersList

    private val lastItemPositionOffset = 4
    private var pageNumber: Long = 1

    fun requestList() {
        viewModelScope.launch(Dispatchers.Main) {
            fetchList()
        }
    }

    fun onRefresh() {
        pageNumber = 1

        viewModelScope.launch(Dispatchers.Main) {
            getAllCharacters.invoke(pageNumber)
                .doOnSuccess { list ->
                    _charactersList.value = list
                    _showRefreshing.value = false
                }
                .doOnError { error -> onDataError(error) }
        }
    }

    fun onScrolled(lastVisibleItemPosition: Int) {
        if (isListEnd(lastVisibleItemPosition)) {
            pageNumber++
            viewModelScope.launch(Dispatchers.Main) {
                getAllCharacters.invoke(pageNumber)
                    .doOnSuccess { nextCharactersList ->
                        _charactersList.value
                            ?.plus(nextCharactersList)
                            .let { updatedCharactersList ->
                                _charactersList.value = updatedCharactersList
                            }
                    }
                    .doOnError { error -> onDataError(error) }
            }
        }
    }

    private fun isListEnd(lastVisibleItemPosition: Int): Boolean {
        val maxItemNumberPosition =
            _charactersList.value?.size?.let { it - lastItemPositionOffset } ?: 0
        return maxItemNumberPosition <= lastVisibleItemPosition - 1
    }

    private suspend fun fetchList() {
        _showLoader.value = true

        getAllCharacters.invoke(pageNumber)
            .doOnSuccess { list ->
                updateList(list)
            }
            .doOnError { error -> onDataError(error) }
    }

    private fun updateList(list: List<Character>) {
        _showEmptyState.value = list.isEmpty()
        _showLoader.value = false
        _charactersList.value = list
    }
}