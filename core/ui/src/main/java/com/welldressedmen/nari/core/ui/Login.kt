package com.welldressedmen.nari.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.welldressedmen.nari.core.designsystem.theme.NariTheme

@Composable
fun LoginBox() {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }
}

@Composable
fun LoginLogo() {
    Box(
        modifier = Modifier
            .size(180.dp),
        contentAlignment = Alignment.Center,
    ) {
//        Image()
    }
}

@Preview
@Composable
fun LoginLogoPreview() {
    NariTheme {
        Surface {
            LoginLogo()
        }
    }
}