package com.example.vkcurrencyconversion.data.network

import com.example.vkcurrencyconversion.data.network.model.ExchangeRate
import com.example.vkcurrencyconversion.data.network.response.ExchangeRateResponse
import com.example.vkcurrencyconversion.domain.model.Currency
import com.example.vkcurrencyconversion.domain.reporitory.CurrencyRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.awaitResponse

class CurrencyRepositoryImpl : CurrencyRepository {

    override suspend fun convert(amount: Double, from: String, to: String): ExchangeRateResponse {
        return try {
            val response: Response<ExchangeRate> = getCurrentRate(from, to).awaitResponse()

            if (response.isSuccessful && response.body() != null) {
                val currentRate = response.body()!!.data[to]!!

               ExchangeRateResponse.Success(
                   getConvertCurrency(
                       amount = amount,
                       currencyRate = currentRate,
                       currencyType = to
                   )
               )

            } else {
                getErrors(response)
            }

        } catch (e: HttpException) {
            ExchangeRateResponse.Error(message = e.message())
        } catch (e: Throwable) {
            ExchangeRateResponse.Exception(e)
        }
    }

    private fun getCurrentRate(from: String, to: String): Call<ExchangeRate> = RetrofitInstance.getExchangeRate(from, to)

    private fun getConvertCurrency(amount: Double, currencyRate: Double, currencyType: String) = Currency(
        amount = amount * currencyRate,
        currencyType =  currencyType
    )

    private fun getErrors(response: Response<ExchangeRate>): ExchangeRateResponse.Error = errorResponse(response)

    private fun errorResponse(response: Response<ExchangeRate>): ExchangeRateResponse.Error {
        val gson = Gson()
        val type = object : TypeToken<ExchangeRateResponse.Error>() {}.type

        return gson.fromJson(response.errorBody()!!.charStream(), type)
    }
}