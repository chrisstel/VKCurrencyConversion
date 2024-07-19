package com.example.vkcurrencyconversion.domain.usecase

import android.util.Log
import com.example.vkcurrencyconversion.domain.model.Currency
import com.example.vkcurrencyconversion.domain.reporitory.CurrencyRepository

class ConvertCurrencyUseCase(
    private val currencyRepository: CurrencyRepository
) {

    suspend operator fun invoke(from: Currency, to: String) = currencyRepository
            .convert(
            from = from,
            to = to
        )
}

