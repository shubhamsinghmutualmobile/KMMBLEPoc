package com.mutualmobile.kmmblepoc.android.ui.screens.details_screen

import androidx.activity.compose.BackHandler
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.mutualmobile.kmmblepoc.viewmodels.MainViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.icerock.moko.mvvm.livedata.asFlow
import org.koin.androidx.compose.get

@Destination
@Composable
fun DetailsScreen(
    navigator: DestinationsNavigator,
    mainViewModel: MainViewModel = get()
) {
    val currentDevice by mainViewModel.currentSelectedDeviceFlow.asFlow().collectAsState(initial = null)

    BackHandler {
        currentDevice?.let { nnCurrentDevice ->
            mainViewModel.disconnectFromDevice(nnCurrentDevice)
            navigator.navigateUp()
        } ?: navigator.navigateUp()
    }
    Text(text = "Details Screen")
}
