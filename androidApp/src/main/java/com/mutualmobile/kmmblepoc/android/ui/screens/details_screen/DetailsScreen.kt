package com.mutualmobile.kmmblepoc.android.ui.screens.details_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mutualmobile.kmmblepoc.viewmodels.MainViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.bluefalcon.BluetoothPeripheral
import dev.icerock.moko.mvvm.livedata.asFlow
import org.koin.androidx.compose.get

@Destination
@Composable
fun DetailsScreen(
    navigator: DestinationsNavigator,
    mainViewModel: MainViewModel = get()
) {
    val currentDevice by mainViewModel.currentSelectedDeviceFlow.asFlow()
        .collectAsState(initial = null)

    val characteristicValue by mainViewModel.characteristicValue.asFlow()
        .collectAsState(initial = "")

    BackHandler {
        onBackPressed(currentDevice, mainViewModel, navigator)
    }
    Scaffold(topBar = {
        TopAppBar(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { onBackPressed(currentDevice, mainViewModel, navigator) }) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = null)
            }
            Text(
                text = "Device Details",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }) {
        LazyColumn(content = {
            item {
                Column(modifier = Modifier.fillMaxSize()) {
                    currentDevice?.let { nnCurrentDevice ->

                        Row() {
                            Text(
                                text = "Device name: ${nnCurrentDevice.name}",
                                style = MaterialTheme.typography.h5
                            )
                            Button(onClick = {
                                mainViewModel.readManufacturer()
                            }) {
                                Text(text = "Read Manufacturer")
                            }
                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                        Text(
                            text = "charcteristic $characteristicValue",
                            style = MaterialTheme.typography.h6
                        )
                        Text(
                            text = "UUID: ${nnCurrentDevice.uuid}",
                            style = MaterialTheme.typography.h6
                        )
                        Spacer(modifier = Modifier.padding(8.dp))
                        Text(
                            text = "RSSI: ${nnCurrentDevice.rssi}",
                            style = MaterialTheme.typography.body1
                        )
                        Spacer(modifier = Modifier.padding(8.dp))
                        nnCurrentDevice.services.forEachIndexed { index, service ->
                            Text(
                                text = "Service ${index + 1}: ${service.name}",
                                style = MaterialTheme.typography.body1
                            )
                            Column() {
                                service.characteristics.forEach {
                                    Text(
                                        text = "Characterisitc ${index + 1}: ${it.name} ${it.value}",
                                        style = MaterialTheme.typography.body1
                                    )
                                }
                            }
                        }
                    }
                }
            }
        })
    }
}

private fun onBackPressed(
    currentDevice: BluetoothPeripheral?,
    mainViewModel: MainViewModel,
    navigator: DestinationsNavigator
) {
    currentDevice?.let { nnCurrentDevice ->
        mainViewModel.disconnectFromDevice(nnCurrentDevice)
        navigator.navigateUp()
    } ?: navigator.navigateUp()
}
