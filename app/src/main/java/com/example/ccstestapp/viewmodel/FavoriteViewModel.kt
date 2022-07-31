package com.example.ccstestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ccstestapp.model.interactor.FavoriteInteractorImpl
import com.example.ccstestapp.model.room.FavoriteEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel
@Inject constructor(
    private val interactor: FavoriteInteractorImpl
) : ViewModel() {
    private val _data = MutableStateFlow<List<FavoriteEntity>>(listOf())
    val data: StateFlow<List<FavoriteEntity>> = _data

    fun gatData() {
        viewModelScope.launch {
            _data.value = interactor.getData()
        }
    }

    fun deleteById(id: Int) {
        viewModelScope.launch {
            interactor.deleteById(id)
        }
    }
}