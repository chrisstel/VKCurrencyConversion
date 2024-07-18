package com.example.vkcurrencyconversion.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vkcurrencyconversion.databinding.FragmentMainBinding
import com.example.vkcurrencyconversion.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMainBinding.inflate(layoutInflater, container, false)
        .also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        views {
//            convertButton.setOnClickListener {
//                viewModel.convert(
//                    amount = 100.00,
//                    from = "USD",
//                    to = "RUB"
//                )
//            }
//        }
    }

    private fun <T> views(block: FragmentMainBinding.() -> T): T? = _binding?.block()
}