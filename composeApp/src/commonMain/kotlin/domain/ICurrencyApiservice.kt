package domain

import domain.model.Currency
import domain.model.RequestState

interface ICurrencyApiservice{
    suspend fun getLatestExchangeRates(): RequestState<List<Currency>>
}