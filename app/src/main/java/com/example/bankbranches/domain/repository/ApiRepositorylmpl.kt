package com.example.bankbranches.domain.repository

import android.content.Context
import com.example.bankbranches.domain.BaseApiResponse
import com.example.bankbranches.domain.Resource
import com.example.bankbranches.domain.model.BankBranch
import com.example.bankbranches.domain.model.BankBranchesResponse
import com.example.bankbranches.domain.services.BankService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApiRepositorylmpl @Inject constructor(
    private val apiService: BankService,
    context: Context,
) : ApiRepository, BaseApiResponse(context) {

    override fun getBankBranches(): Flow<Resource<List<BankBranch>>> {
        return safeApiCall { apiService.getBankBranches() }
    }

}