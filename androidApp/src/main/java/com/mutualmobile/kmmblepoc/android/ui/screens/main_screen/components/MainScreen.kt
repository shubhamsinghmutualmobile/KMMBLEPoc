package com.mutualmobile.kmmblepoc.android.ui.screens.main_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mutualmobile.kmmblepoc.android.R
import com.mutualmobile.kmmblepoc.viewmodels.MainViewModel
import dev.bluefalcon.BluetoothPeripheral
import dev.icerock.moko.mvvm.livedata.asFlow

@Composable
fun MainScreen(
    mainViewModel: MainViewModel
) {
    val ctx = LocalContext.current
    val device by mainViewModel.devicesFlow.asFlow()
        .collectAsState(initial = null)
    val devices by remember {
        mutableStateOf(
            mutableListOf<BluetoothPeripheral>()
        )
    }
    addOrUpdateDevicesInList(device, devices)
    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            ScreenHeader()
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                devices.distinctBy { device -> device.uuid }.forEach { device ->
                    item {
                        ctx // todo: check why this context is needed to display the DeviceCard
                        DeviceCard(device)
                    }
                }
            }
        }
    }
}

private fun addOrUpdateDevicesInList(
    device: BluetoothPeripheral?,
    devices: MutableList<BluetoothPeripheral>
) {
    device?.let { nnDevice ->
        val indexOfExistingElement = devices.indexOf(nnDevice)
        if (indexOfExistingElement != -1) {
            devices[indexOfExistingElement] = nnDevice
        } else {
            devices.add(nnDevice)
        }
    }
}

@Composable
private fun ScreenHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "List of devices nearby:",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(16.dp)
        )
        CircularProgressIndicator(
            modifier = Modifier
                .padding(end = 16.dp)
                .size(24.dp),
            strokeWidth = 2.dp
        )
    }
}

@Composable
private fun DeviceCard(device: BluetoothPeripheral) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(percent = 20)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = device.name.orEmpty(),
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = "UUID: ${device.uuid}",
                    style = MaterialTheme.typography.caption
                )
            }
            Column(
                modifier = Modifier.padding(end = 24.dp)
            ) {
                IconButton(onClick = {}, modifier = Modifier.padding(top = 4.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_circle_right_arrow),
                    contentDescription = null
                )
            }
                Text(
                    text = "Connect",
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
        }
    }
}
