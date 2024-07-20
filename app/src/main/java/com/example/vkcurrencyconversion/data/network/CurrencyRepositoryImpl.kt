package com.example.vkcurrencyconversion.data.network

import com.example.vkcurrencyconversion.data.network.model.ExchangeRate
import com.example.vkcurrencyconversion.domain.model.Currency
import com.example.vkcurrencyconversion.domain.reporitory.CurrencyRepository
import com.example.vkcurrencyconversion.util.Resource
import com.example.vkcurrencyconversion.util.round
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.awaitResponse

class CurrencyRepositoryImpl : CurrencyRepository {

    override suspend fun convert(amount: Double, from: String, to: String): Resource<Currency> {
        return try {
            val response: Response<ExchangeRate> = getCurrentRate(from, to).awaitResponse()

            if (response.isSuccessful && response.body() != null) {
                val currentRate = response.body()!!.data[to]!!

               Resource.Success(
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
            Resource.Error(message = e.message())
        } catch (e: Throwable) {
            Resource.Exception(e)
        }
    }

    private fun getCurrentRate(from: String, to: String): Call<ExchangeRate> = RetrofitInstance.getExchangeRate(from, to)

    private fun getConvertCurrency(amount: Double, currencyRate: Double, currencyType: String) = Currency(
        amount = (amount * currencyRate).round(2),
        currencyType =  currencyType
    )

    private fun getErrors(response: Response<ExchangeRate>): Resource.Error<Currency> = errorResponse(response)

    private fun errorResponse(response: Response<ExchangeRate>): Resource.Error<Currency> {
        val gson = Gson()
        val type = object : TypeToken<Resource.Error<Currency>>() {}.type

        return gson.fromJson(response.errorBody()!!.charStream(), type)
    }
}