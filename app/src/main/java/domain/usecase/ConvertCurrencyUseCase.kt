package domain.usecase

import domain.model.Currency
import domain.reporitory.CurrencyRepository

class ConvertCurrencyUseCase(
    private val currencyRepository: CurrencyRepository
) {

    suspend operator fun invoke(currency: Currency): Currency = currencyRepository.convert(currency)
}