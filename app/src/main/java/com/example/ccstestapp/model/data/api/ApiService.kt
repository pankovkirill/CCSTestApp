package com.example.ccstestapp.model.data.api

import com.example.ccstestapp.model.data.ResponseDataModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("/exchangerates_data/latest")
    fun getLatestAsync(
        @Header("apikey") token: String,
        @Query("base") base: String
    ): Deferred<ResponseDataModel>
}