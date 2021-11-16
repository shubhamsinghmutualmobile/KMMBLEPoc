package com.mutualmobile.kmmblepoc.android.ui.screens.main_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mutualmobile.kmmblepoc.viewmodels.MainViewModel
import dev.icerock.moko.mvvm.livedata.asFlow

@Composable
fun MainScreen(
    mainViewModel: MainViewModel
) {
    Surface {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            val device by mainViewModel.devicesFlow.asFlow()
                .collectAsState(initial = null)
            val devices by remember {
                mutableStateOf(
                    StringBuilder("")
                )
            }
            device?.let { nnDevice -> devices.append("$nnDevice\n") }
            Text(text = devices.toString())
        }
    }
}
