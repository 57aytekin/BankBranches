package com.example.bankbranches.presentation.navigation

import com.example.bankbranches.util.Constants.BANK_BRANCHES_MODEL

sealed class Destination(protected val route: String, vararg params: String) {
    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }

    sealed class NoArgumentsDestination(route: String) : Destination(route) {
        operator fun invoke(): String = route
    }

    object Home : NoArgumentsDestination("HomeScreen")

    object BranchDetail : Destination("BranchDetail", BANK_BRANCHES_MODEL) {
        private const val IS_Activity_Weekly_Plan_Add_KEY = "IS_Activity_Weekly_Plan_Add_KEY"

        operator fun invoke(bankBranchesModel: String): String = route.appendParams(
            IS_Activity_Weekly_Plan_Add_KEY to bankBranchesModel,
        )
    }

}

internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this)

    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("/$arg")
        }
    }

    return builder.toString()
}