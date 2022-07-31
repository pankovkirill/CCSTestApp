package com.example.ccstestapp.model.interactor

import com.example.ccstestapp.model.data.AppState
import com.example.ccstestapp.model.data.DataModel
import com.example.ccstestapp.model.data.RatesModel
import com.example.ccstestapp.model.data.ResponseDataModel
import com.example.ccstestapp.model.repository.RepositoryLocal
import com.example.ccstestapp.model.repository.RepositoryRemote
import com.example.ccstestapp.model.room.FavoriteEntity
import javax.inject.Inject

class MainInteractorImpl
@Inject constructor(
    private val repositoryRemote: RepositoryRemote,
    private val repositoryLocal: RepositoryLocal,
) : MainInteractor {
    override suspend fun saveDataToDb(entity: FavoriteEntity) {
        repositoryLocal.saveToDb(entity)
    }

    override suspend fun getData(orderType: String, base: String): AppState {
        val appState: AppState
        appState =
            AppState.Success(
                convertResponseDataToRatesModel(
                    repositoryRemote.getData(base),
                    orderType
                )
            )
        return appState
    }

    private fun convertResponseDataToRatesModel(
        responseDataModel: ResponseDataModel,
        orderType: String
    ): DataModel {
        return DataModel(
            responseDataModel.base,
            getListFromRates(orderType, responseDataModel.rates)
        )
    }

    private fun getListFromRates(
        orderType: String,
        rates: ResponseDataModel.Rates
    ): List<RatesModel> {
        val list = listOf(
            RatesModel("USD", rates.USD),
            RatesModel("EUR", rates.EUR),
            RatesModel("JPY", rates.JPY),
            RatesModel("GBP", rates.GBP),
            RatesModel("AUD", rates.AUD),
            RatesModel("CAD", rates.CAD),
            RatesModel("CHF", rates.CHF),
            RatesModel("CNY", rates.CNY),
            RatesModel("SEK", rates.SEK),
            RatesModel("MXN", rates.MXN),
            RatesModel("NZD", rates.NZD),
            RatesModel("SGD", rates.SGD),
            RatesModel("HKD", rates.HKD),
            RatesModel("NOK", rates.NOK),
            RatesModel("KRW", rates.KRW),
            RatesModel("TRY", rates.TRY),
            RatesModel("INR", rates.INR),
            RatesModel("RUB", rates.RUB),
            RatesModel("BRL", rates.BRL),
            RatesModel("ZAR", rates.ZAR),
            RatesModel("DKK", rates.DKK),
            RatesModel("PLN", rates.PLN),
            RatesModel("TWD", rates.TWD),
            RatesModel("THB", rates.THB),
            RatesModel("MYR", rates.MYR),
            RatesModel("UAH", rates.UAH),
            RatesModel("KZT", rates.KZT),
        )
        return when (orderType) {
            A_TO_Z_ORDER -> {
                list.sortedBy { it.name }
            }
            Z_TO_A_ORDER -> {
                list.sortedByDescending { it.name }
            }
            ASCENDING -> {
                list.sortedBy { it.value }
            }
            DESCENDING -> {
                list.sortedByDescending { it.value }
            }
            else -> {
                list.sortedBy { it.name }
            }
        }
    }

    companion object {
        const val A_TO_Z_ORDER = "from a to z"
        const val Z_TO_A_ORDER = "from z to a"
        const val ASCENDING = "ascending"
        const val DESCENDING = "descending"
    }
}