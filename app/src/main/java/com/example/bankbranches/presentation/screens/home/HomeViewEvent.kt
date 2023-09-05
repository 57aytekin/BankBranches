package com.example.bankbranches.presentation.screens.home

import com.example.bankbranches.presentation.base.IViewEvent

sealed class HomeViewEvent: IViewEvent {
    class SnackBarError(val message: String?) : HomeViewEvent()
    object GetBankBranches: HomeViewEvent()
}