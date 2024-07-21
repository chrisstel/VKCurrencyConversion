package com.example.vkcurrencyconversion.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vkcurrencyconversion.databinding.FragmentConversionBinding
import com.example.vkcurrencyconversion.domain.model.Currency
import com.example.vkcurrencyconversion.presentation.MainActivity
import com.example.vkcurrencyconversion.presentation.viewmodel.MainViewModel
import com.example.vkcurrencyconversion.util.Resource
import com.example.vkcurrencyconversion.util.currencies

class ConversionFragment : Fragment() {
    private var _binding: FragmentConversionBinding? = null
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentConversionBinding.inflate(layoutInflater, container, false)
        .also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        handleSuccessResponse()
    }

    private fun handleSuccessResponse() {
        viewModel.exchangeResponse.observe(viewLifecycleOwner) { response ->
            when {
                response is Resource.Success -> {
                    showCurrentCurrency()
                    showConvertedCurrency(response.result)
                }
            }
        }
    }

    private fun showCurrentCurrency() {
        views {
            viewModel.currency.observe(viewLifecycleOwner) {
                val currencySymbol = currencySymbol(it.currencyType)
                val currentCurrency = "${it.amount} $currencySymbol"

                this.currentCurrency.text = currentCurrency
            }
        }
    }

    private fun showConvertedCurrency(responseResult: Currency) {
        views {
            val currencySymbol = currencySymbol(responseResult.currencyType)
            val convertedCurrency = "${responseResult.amount} $currencySymbol"

            this.convertedCurrency.text = convertedCurrency
        }
    }

    private fun currencySymbol(currencyType: String) = currencies[currencyType]

    private fun <T> views(block: FragmentConversionBinding.() -> T): T? = _binding?.block()
}