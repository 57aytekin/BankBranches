package com.example.bankbranches.presentation.screens.home

import com.example.bankbranches.domain.model.BankBranch
import com.example.bankbranches.presentation.base.IViewState

data class HomeViewState(
    val isLoading: Boolean = false,
    val bankList: List<BankBranch>? = listOf(),
    val searchedBankList: List<BankBranch>? = listOf(),
): IViewState
