package com.example.bankbranches.domain

sealed class Resource<T> {
    class Success<T>(val data: T?) : Resource<T>()
    class Loading<T> : Resource<T>()
    class Error<T>(val apiError: String?) : Resource<T>()
}