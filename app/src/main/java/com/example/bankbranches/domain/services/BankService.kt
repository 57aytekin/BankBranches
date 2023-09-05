package com.example.bankbranches.domain.services

import com.example.bankbranches.domain.model.BankBranch
import com.example.bankbranches.domain.model.BankBranchesResponse
import retrofit2.Response
import retrofit2.http.GET

interface BankService {
    @GET("fatiha380/mockjson/main/bankdata")
    suspend fun getBankBranches(): Response<List<BankBranch>>
}