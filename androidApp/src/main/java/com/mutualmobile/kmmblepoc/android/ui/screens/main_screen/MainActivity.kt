package com.mutualmobile.kmmblepoc.android.ui.screens.main_screen

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mutualmobile.kmmblepoc.android.ui.screens.main_screen.components.MainScreen
import com.mutualmobile.kmmblepoc.android.ui.theme.KMMBlePocTheme
import com.mutualmobile.kmmblepoc.android.utils.askForBluetoothPermissions
import com.mutualmobile.kmmblepoc.android.utils.launchLifecycleScope
import com.mutualmobile.kmmblepoc.viewmodels.MainViewModel
import dev.icerock.moko.mvvm.livedata.asFlow
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        askForBluetoothPermissions(mainViewModel)

        setContent {
            KMMBlePocTheme {
                MainScreen(mainViewModel = mainViewModel)
            }
        }

        launchLifecycleScope {
            mainViewModel.permissionsFlow.asFlow().collectLatest { arePermissionsGranted ->
                if (arePermissionsGranted) {
                    mainViewModel.searchDevices(applicationContext = application)
                }
            }
        }
    }
}
