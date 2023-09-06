package com.example.bankbranches.presentation.screens.detail

import com.example.bankbranches.presentation.base.IViewEvent

sealed class BranchDetailViewEvent: IViewEvent {
    class SnackBarError(val message: String?) : BranchDetailViewEvent()
}