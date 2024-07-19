package com.example.vkcurrencyconversion.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vkcurrencyconversion.databinding.FragmentConversionBinding
import com.example.vkcurrencyconversion.presentation.MainActivity
import com.example.vkcurrencyconversion.presentation.viewmodel.MainViewModel

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

        views {
            viewModel.currency.observe(viewLifecycleOwner) { currentCurrency ->
                textTV.text = currentCurrency.toString()
            }
        }
    }

    private fun <T> views(block: FragmentConversionBinding.() -> T): T? = _binding?.block()
}