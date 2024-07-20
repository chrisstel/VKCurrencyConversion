package com.example.vkcurrencyconversion.util

import com.example.vkcurrencyconversion.data.network.model.Errors

sealed class Resource<T> {

    data class Success<T>(val result: T) : Resource<T>()

    class Loading<T>: Resource<T>()

    data class Error<T>(
        val message: String? = null,
        val errors: Errors? = null,
        val info: String? = null
    ) : Resource<T>()

    data class Exception<T>(val e: Throwable) : Resource<T>()
}