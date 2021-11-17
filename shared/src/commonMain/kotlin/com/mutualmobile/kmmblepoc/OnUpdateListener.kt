package com.mutualmobile.kmmblepoc

import dev.bluefalcon.BluetoothPeripheral

interface OnUpdateListener {
    fun update(list: BluetoothPeripheral?)
}
