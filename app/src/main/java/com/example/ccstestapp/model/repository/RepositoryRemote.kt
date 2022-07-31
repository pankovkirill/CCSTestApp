package com.example.ccstestapp.model.repository

import com.example.ccstestapp.model.data.ResponseDataModel

interface RepositoryRemote {
    suspend fun getData(base: String): ResponseDataModel
}