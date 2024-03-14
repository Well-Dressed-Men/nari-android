package com.welldressedmen.nari.feature.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.welldressedmen.nari.feature.login.LoginRoute

const val LOGIN_ROUTE = "login_route"

fun NavController.navigateToLogin(navOptions: NavOptions) = navigate(LOGIN_ROUTE, navOptions)
fun NavGraphBuilder.loginScreen(
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    composable(route = LOGIN_ROUTE) {
        LoginRoute(onShowSnackbar = onShowSnackbar)
    }
}