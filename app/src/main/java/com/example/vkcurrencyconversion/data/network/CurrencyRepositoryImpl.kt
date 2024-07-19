package com.example.vkcurrencyconversion.data.network

import android.util.Log
import com.example.vkcurrencyconversion.data.network.response.ErrorResponse
import com.example.vkcurrencyconversion.data.network.response.ExchangeRate
import com.example.vkcurrencyconversion.data.network.response.ExchangeRateResponse
import com.example.vkcurrencyconversion.domain.model.Currency
import com.example.vkcurrencyconversion.domain.reporitory.CurrencyRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.awaitResponse

const val TAG = "RETROFIT"

class CurrencyRepositoryImpl() : CurrencyRepository {

    override suspend fun convert(from: Currency, to: String): ExchangeRateResponse {
        return try {
            Log.d(TAG, "Send request")
            val response: Response<ExchangeRate> = getCurrentRate(from.currencyType, to).awaitResponse()

            if (response.isSuccessful && response.body() != null) {
                Log.d(TAG, "SUCCESS!!!! ${response.body()!!.data[to]}")
                ExchangeRateResponse.Success(
                    exchangeRate = response.body()!!
                )
            } else {
                val errors = getErrors(response)

                Log.d(TAG, errors.joinToString())

                ExchangeRateResponse.Error(
                    errors = "Error"
                )
            }
        } catch (e: HttpException) {
            Log.d(TAG, "Error: HTTPException")
            ExchangeRateResponse.Error(
                errors = "Exceptions"
            )
        } catch (e: Throwable) {
            Log.d(TAG, "Throwable exception ${e.message}")
            ExchangeRateResponse.Exception(e)
        }
    }

    private suspend fun getCurrentRate(from: String, to: String): Call<ExchangeRate> = RetrofitInstance.getExchangeRate(from, to)

    private fun getErrors(response: Response<ExchangeRate>): List<String> {
        val errorResponse = errorResponse(response)
        val errorList = mutableListOf<String>()

        errorResponse?.errors?.base_currency?.let { baseCurrencyErrors -> errorList += baseCurrencyErrors }
        errorResponse?.errors?.currencies?.let { currenciesErrors -> errorList += currenciesErrors }

        return errorList
    }

    private fun errorResponse(response: Response<ExchangeRate>): ErrorResponse? {
        val gson = Gson()
        val type = object : TypeToken<ErrorResponse>() {}.type

        return gson.fromJson(response.errorBody()!!.charStream(), type)
    }
}