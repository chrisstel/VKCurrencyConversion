package com.example.vkcurrencyconversion.presentation.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vkcurrencyconversion.App
import com.example.vkcurrencyconversion.domain.model.Currency
import com.example.vkcurrencyconversion.domain.usecase.ConvertCurrencyUseCase
import com.example.vkcurrencyconversion.utils.response.ExchangeRateResponse
import kotlinx.coroutines.launch

class MainViewModel(
    app: App,
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
) : AndroidViewModel(app) {
    private val _currency = MutableLiveData<Currency>()
    val currency: LiveData<Currency> = _currency

    private val _exchangedCurrency = MutableLiveData<Currency>()
    val exchangedCurrency: LiveData<Currency> = _exchangedCurrency

    fun convert(amount: Double, from: String, to: String) = viewModelScope.launch {
        saveCurrency(amount, from)


        val result = convertCurrencyUseCase(amount = amount, from = from, to = to)

        when {
                result is ExchangeRateResponse.Success -> _exchangedCurrency.value = result.currency
        }
    }


    private fun saveCurrency(amount: Double, currencyType: String) {
        _currency.value = Currency(
            amount = amount,
            currencyType = currencyType
        )
    }
}