package com.welldressedmen.nari.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.welldressedmen.nari.core.data.util.NetworkMonitor
import com.welldressedmen.nari.core.designsystem.component.NariBackground
import com.welldressedmen.nari.navigation.NariNavHost
import com.welldressedmen.nari.navigation.TopLevelDestination

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NariApp(
    networkMonitor: NetworkMonitor,
    appState: NariAppState = rememberNariAppState(
        networkMonitor = networkMonitor,
    )
) {
    val shouldShowTopAndBottomBar = appState.currentTopLevelDestination == TopLevelDestination.MAIN_CONTENT

    NariBackground {

        val snackbarHostState = remember { SnackbarHostState() }

        val isOffline by appState.isOffline.collectAsStateWithLifecycle()

        val notConnectedMessage = "네트워크 연결이 끊어졌습니다. \uD83D\uDE15"
        LaunchedEffect(isOffline) {
            if (isOffline) {
                snackbarHostState.showSnackbar(
                    message = notConnectedMessage,
                    duration = SnackbarDuration.Indefinite,
                )
            }
        }

        Scaffold(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            bottomBar = {
                if (shouldShowTopAndBottomBar) {

                }
            }
        ) { padding ->
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal),
                    ),
            ) {
                Column(
                    Modifier.fillMaxSize()
                ) {
                    val destination = appState.currentTopLevelDestination

                    if (destination == TopLevelDestination.MAIN_CONTENT) {
                        // add TopAppBar
                    }

                    NariNavHost(appState = appState, onShowSnackbar = { message, action ->
                        snackbarHostState.showSnackbar(
                            message = message,
                            actionLabel = action,
                            duration = SnackbarDuration.Short,
                        ) == SnackbarResult.ActionPerformed
                    })
                }
            }
        }
    }
}