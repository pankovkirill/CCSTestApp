package com.example.ccstestapp.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataModel(
    val base: String,
    val rates: List<RatesModel>
) : Parcelable