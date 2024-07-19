package com.example.vkcurrencyconversion.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.vkcurrencyconversion.domain.model.Currency
import com.example.vkcurrencyconversion.domain.usecase.ConvertCurrencyUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
) : ViewModel() {
    private val _currency = MutableLiveData<Currency>()
    val currency: LiveData<Currency> = _currency

    private val _exchangedCurrency = MutableLiveData<Currency>()
    val exchangedCurrency: LiveData<Currency> = _exchangedCurrency

    fun convert(amount: Double, from: String, to: String) = viewModelScope.launch {
        saveCurrency(amount, from)

//        convertCurrencyUseCase(
//            amount = amount,
//            from = from,
//            to = to
//        )
    }

    private fun saveCurrency(amount: Double, currencyType: String) {
        _currency.value = Currency(
            amount = amount,
            currencyType = currencyType
        )
    }
}