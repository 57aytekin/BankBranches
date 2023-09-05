package com.example.bankbranches.domain

import android.content.Context
import com.example.bankbranches.util.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import retrofit2.Response

open class BaseApiResponse(
    private val context: Context,
) {
    private val networkAvailable get() = NetworkUtil.isNetworkAvailable(context)

    protected fun <T> safeApiCall(call: suspend () -> Response<T>): Flow<Resource<T>> {
        return flow<Resource<T>> {
            if (networkAvailable) {
                val response = call()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) emit(Resource.Success(body))
                    else
                        emit(Resource.Success(response.body()))
                } else {
                    emit(Resource.Error("Service error!"))
                }
            }else{
                emit(Resource.Error("İnternet bağlantınızı kontrol ediniz"))
            }
        }
            .catch {
                emit(Resource.Error(it.message))
            }
            .onStart { emit(Resource.Loading()) }
            .flowOn(Dispatchers.IO)
    }
}