package com.example.ccstestapp.model.data

sealed class AppState {
    data class Success(val data: DataModel?) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
    object Empty: AppState()
}
