package com.example.ccstestapp.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RatesModel(
    val name: String,
    val value: Double
) : Parcelable
