package data.remote

import domain.model.Currency
import domain.reporitory.CurrencyRepository

class CurrencyRepositoryImpl : CurrencyRepository {

    override suspend fun convert(currency: Currency): Currency {
        TODO("Not yet implemented")
    }
}