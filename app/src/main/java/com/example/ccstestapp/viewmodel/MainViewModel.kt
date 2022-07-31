package com.example.ccstestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ccstestapp.model.interactor.MainInteractorImpl
import com.example.ccstestapp.model.data.AppState
import com.example.ccstestapp.model.room.FavoriteEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel
@Inject constructor(
    private val interactor: MainInteractorImpl
) : ViewModel() {

    private val _data = MutableStateFlow<AppState>(AppState.Empty)
    val data: StateFlow<AppState> = _data

    fun getData(orderType: String, base: String) = viewModelScope.launch {
        _data.value = (AppState.Loading)
        try {
            _data.value = (interactor.getData(orderType, base))
        } catch (e: Throwable) {
            _data.value = (AppState.Error(e))
        }
    }

    fun addRateToFavoriteList(name: String) {
        viewModelScope.launch {
            interactor.saveDataToDb(
                FavoriteEntity(
                    BASE_FAVORITE_TABLE_ID,
                    name
                )
            )
        }
    }

    companion object {
        private const val BASE_FAVORITE_TABLE_ID = 0
    }
}