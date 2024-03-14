package com.welldressedmen.nari.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.welldressedmen.nari.feature.login.navigation.LOGIN_ROUTE
import com.welldressedmen.nari.feature.login.navigation.loginScreen
import com.welldressedmen.nari.ui.NariAppState

@Composable
fun NariNavHost(
    appState: NariAppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = USER_DATA_ROUTE,
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        userDataNavHost(onShowSnackbar = onShowSnackbar, modifier = modifier)
    }
}

const val USER_DATA_ROUTE = "user_data_route"

fun NavGraphBuilder.userDataNavHost(
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
) {
    composable(route = USER_DATA_ROUTE) {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = LOGIN_ROUTE,
            modifier = modifier
        ) {
            loginScreen(onShowSnackbar = onShowSnackbar)
        }
    }
}