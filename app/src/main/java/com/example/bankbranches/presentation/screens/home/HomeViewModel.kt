package com.example.bankbranches.presentation.screens.home

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.example.bankbranches.domain.Resource
import com.example.bankbranches.domain.repository.ApiRepositorylmpl
import com.example.bankbranches.presentation.base.BaseViewModel
import com.example.bankbranches.util.Constants.EVENT_TAG_DETAIL
import com.example.bankbranches.util.firebase.FirebaseProvider
import com.google.firebase.analytics.FirebaseAnalytics
import com.squareup.moshi.Moshi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    private val apiRepository: ApiRepositorylmpl,
    private val firebaseProvider: FirebaseProvider,
    val moshi: Moshi,
): BaseViewModel<HomeViewState, HomeViewEvent>() {

    init {
        getBankBranches()
    }
    fun setCitySearch(search: String){
        val searchedList = currentState.bankList?.filter { it.sehir?.contains(search, true)  == true }
        viewModelScope.launch {
            setState {
                currentState.copy(
                    searchText = search,
                    searchedBankList = searchedList
                )
            }
        }
    }
    fun clearSearch(){
        viewModelScope.launch {
            setState {
                currentState.copy(
                    searchText = "",
                    searchedBankList = currentState.bankList
                )
            }
        }
    }

    private fun getBankBranches(){
        viewModelScope.launch {
            apiRepository.getBankBranches().collect{
                when (it) {
                    is Resource.Loading -> {
                        setState { currentState.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        setState { currentState.copy(
                            isLoading = false,
                            bankList = it.data,
                            searchedBankList = it.data
                        ) }

                    }
                    is Resource.Error -> {
                        setState { currentState.copy(isLoading = false) }
                        setEvent(HomeViewEvent.SnackBarError(it.apiError))
                    }
                }
            }
        }
    }


    override fun createInitialState() = HomeViewState()

    override fun onTriggerEvent(event: HomeViewEvent) {
        viewModelScope.launch {
            when(event){
                is HomeViewEvent.GetBankBranches -> getBankBranches()
                else -> {}
            }
        }
    }
}