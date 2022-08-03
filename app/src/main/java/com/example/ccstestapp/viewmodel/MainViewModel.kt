package com.example.ccstestapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ccstestapp.model.interactor.MainInteractorImpl
import com.example.ccstestapp.model.data.AppState
import com.example.ccstestapp.model.data.CurrencyModel
import com.example.ccstestapp.model.data.DataModel
import com.example.ccstestapp.model.data.OrderType
import com.example.ccstestapp.model.interactor.MainInteractor
import com.example.ccstestapp.model.room.FavoriteEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel
@Inject constructor(
    private val interactor: MainInteractor
) : ViewModel() {

    private val currencyList = arrayListOf<CurrencyModel>()

    private val _data = MutableStateFlow<AppState>(AppState.Empty)
    val data: StateFlow<AppState> = _data

    fun getData(base: String) = viewModelScope.launch {
        _data.value = (AppState.Loading)
        try {
            val responseData = interactor.getData(base)
            _data.value = responseData
            if (responseData is AppState.Success)
                responseData.data?.let { currencyList.addAll(it.rates) }
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

    fun orderCurrencyList(orderType: OrderType) = viewModelScope.launch {
        val appState = _data.value
        if (appState is AppState.Success) {
            appState.data?.let { dataModel ->
                val currencyList = when (orderType) {
                    OrderType.A_TO_Z -> dataModel.rates.sortedBy { it.name }
                    OrderType.Z_TO_A -> dataModel.rates.sortedByDescending { it.name }
                    OrderType.ASCENDING -> dataModel.rates.sortedBy { it.value }
                    OrderType.DESCENDING -> dataModel.rates.sortedByDescending { it.value }
                }
                val data = DataModel(
                    dataModel.base,
                    currencyList
                )

                _data.value = AppState.Success(data)
            }
        }
    }

    companion object {
        private const val BASE_FAVORITE_TABLE_ID = 0
    }
}