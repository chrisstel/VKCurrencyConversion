package com.example.vkcurrencyconversion.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkcurrencyconversion.domain.usecase.ConvertCurrencyUseCase
import kotlinx.coroutines.launch
import  com.example.vkcurrencyconversion.domain.model.Currency

class MainViewModel(
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
) : ViewModel() {

    fun convert(amount: Double, from: String, to: String) = viewModelScope.launch {
        val from = Currency(
            amount = amount,
            currencyType = from
        )

        convertCurrencyUseCase(
            from = from,
            to = to
        )
    }

}