package com.mutualmobile.kmmblepoc.android.ui.screens.main_screen

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.mutualmobile.kmmblepoc.android.ui.theme.KMMBlePocTheme
import com.mutualmobile.kmmblepoc.android.utils.askForBluetoothPermissions
import com.mutualmobile.kmmblepoc.android.utils.launchLifecycleScope
import com.mutualmobile.kmmblepoc.viewmodels.MainViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import dev.icerock.moko.mvvm.livedata.asFlow
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        askForBluetoothPermissions(mainViewModel)

        setContent {
            KMMBlePocTheme {
                DestinationsNavHost()
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
