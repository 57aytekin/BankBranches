package com.example.bankbranches.presentation.screens.detail

import android.os.Bundle
import com.example.bankbranches.domain.repository.ApiRepositorylmpl
import com.example.bankbranches.presentation.base.BaseViewModel
import com.example.bankbranches.util.Constants
import com.example.bankbranches.util.firebase.FirebaseProvider
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BranchDetailViewModel @Inject constructor(
    private val apiRepository: ApiRepositorylmpl,
    private val firebaseProvider: FirebaseProvider,
): BaseViewModel<BranchDetailViewState, BranchDetailViewEvent>() {

    fun sendScreenViewEventForAnalytics() {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.METHOD, Constants.EVENT_TAG_DETAIL)
        firebaseProvider.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }
    override fun createInitialState() =  BranchDetailViewState()

    override fun onTriggerEvent(event: BranchDetailViewEvent) {
        TODO("Not yet implemented")
    }

}