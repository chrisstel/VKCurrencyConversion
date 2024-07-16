package com.example.vkcurrencyconversion.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vkcurrencyconversion.databinding.FragmentConversionBinding

class ConversionFragment : Fragment() {
    private var _binding: FragmentConversionBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentConversionBinding.inflate(layoutInflater, container, false)
        .also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun <T> views(block: FragmentConversionBinding.() -> T): T? = _binding?.block()
}