package com.example.bankbranches.domain.repository

import com.example.bankbranches.domain.Resource
import com.example.bankbranches.domain.model.BankBranch
import com.example.bankbranches.domain.model.BankBranchesResponse
import kotlinx.coroutines.flow.Flow

interface ApiRepository {
    fun getBankBranches(): Flow<Resource<List<BankBranch>>>
}