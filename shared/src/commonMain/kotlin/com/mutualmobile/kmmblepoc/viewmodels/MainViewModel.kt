package com.mutualmobile.kmmblepoc.viewmodels

import dev.bluefalcon.ApplicationContext
import dev.bluefalcon.BlueFalcon
import dev.bluefalcon.BlueFalconDelegate
import dev.bluefalcon.BluetoothCharacteristic
import dev.bluefalcon.BluetoothCharacteristicDescriptor
import dev.bluefalcon.BluetoothPeripheral
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel

private const val TAG = "MainViewModel"

class MainViewModel : ViewModel() {

    private val _permissionsFlow: MutableLiveData<Boolean> = MutableLiveData(initialValue = false)
    val permissionsFlow: LiveData<Boolean> = _permissionsFlow

    private val _devicesFlow: MutableLiveData<String> = MutableLiveData("")
    val devicesFlow: LiveData<String> = _devicesFlow

    fun setPermission(isPermissionGranted: Boolean) {
        _permissionsFlow.postValue(isPermissionGranted)
    }

    fun searchDevices(applicationContext: ApplicationContext) {
        val blueFalcon = BlueFalcon(applicationContext, null)
        with(blueFalcon) {
            scan()
            delegates.add(
                object : BlueFalconDelegate {
                    override fun didCharacteristcValueChanged(
                        bluetoothPeripheral: BluetoothPeripheral,
                        bluetoothCharacteristic: BluetoothCharacteristic
                    ) {
                        TODO("Not yet implemented")
                    }

                    override fun didConnect(bluetoothPeripheral: BluetoothPeripheral) {
                        TODO("Not yet implemented")
                    }

                    override fun didDisconnect(bluetoothPeripheral: BluetoothPeripheral) {
                        TODO("Not yet implemented")
                    }

                    override fun didDiscoverCharacteristics(bluetoothPeripheral: BluetoothPeripheral) {
                        TODO("Not yet implemented")
                    }

                    override fun didDiscoverDevice(bluetoothPeripheral: BluetoothPeripheral) {
                        _devicesFlow.postValue("${bluetoothPeripheral.name} - ${bluetoothPeripheral.uuid}\n")
                    }

                    override fun didDiscoverServices(bluetoothPeripheral: BluetoothPeripheral) {
                        TODO("Not yet implemented")
                    }

                    override fun didReadDescriptor(
                        bluetoothPeripheral: BluetoothPeripheral,
                        bluetoothCharacteristicDescriptor: BluetoothCharacteristicDescriptor
                    ) {
                        TODO("Not yet implemented")
                    }

                    override fun didRssiUpdate(bluetoothPeripheral: BluetoothPeripheral) {
                        TODO("Not yet implemented")
                    }

                    override fun didUpdateMTU(bluetoothPeripheral: BluetoothPeripheral) {
                        TODO("Not yet implemented")
                    }
                }
            )
        }
    }
}
