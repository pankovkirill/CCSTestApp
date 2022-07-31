package com.example.ccstestapp.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseDataModel(
    val base: String,
    val rates: Rates
) : Parcelable {
    @Parcelize
    data class Rates(
        val USD: Double,
        val EUR: Double,
        val JPY: Double,
        val GBP: Double,
        val AUD: Double,
        val CAD: Double,
        val CHF: Double,
        val CNY: Double,
        val SEK: Double,
        val MXN: Double,
        val NZD: Double,
        val SGD: Double,
        val HKD: Double,
        val NOK: Double,
        val KRW: Double,
        val TRY: Double,
        val INR: Double,
        val RUB: Double,
        val BRL: Double,
        val ZAR: Double,
        val DKK: Double,
        val PLN: Double,
        val TWD: Double,
        val THB: Double,
        val MYR: Double,
        val UAH: Double,
        val KZT: Double,
    ) : Parcelable
}