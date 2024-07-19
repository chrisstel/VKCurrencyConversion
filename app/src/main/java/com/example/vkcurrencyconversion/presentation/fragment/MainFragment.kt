package com.example.vkcurrencyconversion.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.vkcurrencyconversion.R
import com.example.vkcurrencyconversion.databinding.FragmentMainBinding
import com.example.vkcurrencyconversion.presentation.MainActivity
import com.example.vkcurrencyconversion.presentation.viewmodel.MainViewModel

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMainBinding.inflate(layoutInflater, container, false)
        .also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        views {
            convertButton.setOnClickListener {
                convertCurrency()
            }
        }
    }

    private fun convertCurrency() {
        views {
            val amount = amount.text.toString()
            val currencyTypeFrom = currencyMenuFrom.selectedItem.toString()
            val currencyTypeTo = currencyMenuTo.selectedItem.toString()

            if (amount.isNotEmpty()) {
                viewModel.convert(
                    amount = amount.toDouble(),
                    from = currencyTypeFrom,
                    to = currencyTypeTo
                )

                clearViews()
            } else {
                Toast.makeText(context, R.string.empty_field_error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearViews() {
        views {
            amount.text?.clear()
            currencyMenuFrom.setSelection(0)
            currencyMenuTo.setSelection(0)
        }
    }
    private fun <T> views(block: FragmentMainBinding.() -> T): T? = _binding?.block()
}