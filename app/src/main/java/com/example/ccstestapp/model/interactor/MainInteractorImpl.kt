package com.example.ccstestapp.model.interactor

import com.example.ccstestapp.model.data.AppState
import com.example.ccstestapp.model.data.DataModel
import com.example.ccstestapp.model.data.CurrencyModel
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

    override suspend fun getData(base: String): AppState {
        return AppState.Success(
            convertResponseDataToDataModel(
                repositoryRemote.getData(base)
            )
        )
    }

    private fun convertResponseDataToDataModel(
        responseDataModel: ResponseDataModel,
    ): DataModel {
        return DataModel(
            responseDataModel.base,
            getListFromRates(responseDataModel.rates)
        )
    }

    private fun getListFromRates(
        rates: ResponseDataModel.Rates
    ) = listOf(
        CurrencyModel("USD", rates.USD),
        CurrencyModel("EUR", rates.EUR),
        CurrencyModel("JPY", rates.JPY),
        CurrencyModel("GBP", rates.GBP),
        CurrencyModel("AUD", rates.AUD),
        CurrencyModel("CAD", rates.CAD),
        CurrencyModel("CHF", rates.CHF),
        CurrencyModel("CNY", rates.CNY),
        CurrencyModel("SEK", rates.SEK),
        CurrencyModel("MXN", rates.MXN),
        CurrencyModel("NZD", rates.NZD),
        CurrencyModel("SGD", rates.SGD),
        CurrencyModel("HKD", rates.HKD),
        CurrencyModel("NOK", rates.NOK),
        CurrencyModel("KRW", rates.KRW),
        CurrencyModel("TRY", rates.TRY),
        CurrencyModel("INR", rates.INR),
        CurrencyModel("RUB", rates.RUB),
        CurrencyModel("BRL", rates.BRL),
        CurrencyModel("ZAR", rates.ZAR),
        CurrencyModel("DKK", rates.DKK),
        CurrencyModel("PLN", rates.PLN),
        CurrencyModel("TWD", rates.TWD),
        CurrencyModel("THB", rates.THB),
        CurrencyModel("MYR", rates.MYR),
        CurrencyModel("UAH", rates.UAH),
        CurrencyModel("KZT", rates.KZT),
    )
}