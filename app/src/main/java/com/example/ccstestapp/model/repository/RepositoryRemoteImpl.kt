package com.example.ccstestapp.model.repository

import com.example.ccstestapp.model.data.ResponseDataModel
import com.example.ccstestapp.model.data.api.ApiService
import javax.inject.Inject

class RepositoryRemoteImpl
@Inject constructor(
    private val apiService: ApiService
) : RepositoryRemote {
    override suspend fun getData(base: String): ResponseDataModel {
        return apiService.getLatestAsync(API_KEY, base).await()
    }

    companion object {
        private const val API_KEY = "JUZvS6GjdihZaEsZzAJSw0v6lblZ0p56"
    }
}