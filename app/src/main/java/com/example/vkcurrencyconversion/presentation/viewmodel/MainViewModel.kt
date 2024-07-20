package com.example.vkcurrencyconversion.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_ETHERNET
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vkcurrencyconversion.App
import com.example.vkcurrencyconversion.R
import com.example.vkcurrencyconversion.domain.model.Currency
import com.example.vkcurrencyconversion.domain.usecase.ConvertCurrencyUseCase
import com.example.vkcurrencyconversion.util.Resource
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(
    app: Application,
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
) : AndroidViewModel(app) {
    private val _currency = MutableLiveData<Currency>()
    val currency: LiveData<Currency> = _currency

    private val _exchangeResponse: MutableLiveData<Resource<Currency>> = MutableLiveData()
    val exchangeResponse: LiveData<Resource<Currency>> = _exchangeResponse

    fun convert(amount: Double, from: String, to: String) = viewModelScope.launch {
        _exchangeResponse.postValue(Resource.Loading())

        try {
            if (hasInternetConnection()) {
                saveCurrency(amount, from)

                val response = convertCurrencyUseCase(amount = amount, from = from, to = to)
                _exchangeResponse.postValue(response)
            } else {
                setError()
            }
        } catch (t: Throwable) {
            when(t) {
                is IOException -> {
                    val message = getApplication<App>().resources.getString(R.string.internet_error)
                    _exchangeResponse.postValue(Resource.Error(message = message))
                }
                else ->  {
                    val message = getApplication<App>().resources.getString(R.string.conversion_error)
                    _exchangeResponse.postValue(Resource.Error(message = message))
                }
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<App>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when {
            capabilities.hasTransport(TRANSPORT_WIFI) -> true
            capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    private fun saveCurrency(amount: Double, currencyType: String) {
        _currency.value = Currency(
            amount = amount,
            currencyType = currencyType
        )
    }

    private fun setError() {
        _exchangeResponse.postValue(error())
    }

    private fun error() = Resource.Error<Currency>(
        message = getApplication<App>().resources.getString(R.string.no_internet_connection_error)
    )
}