package data.remote.api

import domain.model.Currency
import retrofit2.http.GET

interface ExchangeCurrencyApi {

    @GET()
    fun convert(currency: Currency): Currency
}