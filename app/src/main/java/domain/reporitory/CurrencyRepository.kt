package domain.reporitory

import domain.model.Currency

interface CurrencyRepository {

    suspend fun convert(currency: Currency): Currency
}