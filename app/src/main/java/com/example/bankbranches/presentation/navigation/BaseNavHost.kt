package com.example.bankbranches.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.bankbranches.domain.model.BankBranch
import com.example.bankbranches.presentation.screens.detail.BranchDetailScreen
import com.example.bankbranches.presentation.screens.home.HomeScreen
import com.example.bankbranches.util.Constants
import com.example.bankbranches.util.decodeAddress
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

@Composable
fun BaseNavHost(
    navController: NavHostController,
    moshi: Moshi
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Home
    ) {
        composable(destination = Destination.Home) {
            HomeScreen(navController = navController)
        }

        composable(
            destination = Destination.BranchDetail,
        ) {
            val bankDTO: String? = it.arguments?.getString(Constants.BANK_BRANCHES_MODEL)
            val jsonAdapter: JsonAdapter<BankBranch> = moshi.adapter(BankBranch::class.java)
            val bankBranch = jsonAdapter.fromJson(bankDTO ?: "")

            BranchDetailScreen(
                navController = navController,
                bankBranch = bankBranch?.copy(
                    bankaSube = bankBranch.bankaSube?.decodeAddress(),
                    enYakinAtm = bankBranch.enYakinAtm?.decodeAddress(),
                    adres = bankBranch.adres?.decodeAddress(),
                    adresAdi = bankBranch.adresAdi?.decodeAddress(),
                )
            )

        }
    }
}