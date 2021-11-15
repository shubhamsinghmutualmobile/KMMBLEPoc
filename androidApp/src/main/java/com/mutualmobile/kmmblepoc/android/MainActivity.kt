package com.mutualmobile.kmmblepoc.android

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mutualmobile.kmmblepoc.Greeting
import com.mutualmobile.kmmblepoc.android.utils.askForBluetoothPermissions
import com.mutualmobile.kmmblepoc.viewmodels.MainViewModel
import dev.icerock.moko.mvvm.livedata.asFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val listOfDevices: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()
        askForBluetoothPermissions(mainViewModel)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                mainViewModel.permissionsFlow.asFlow().collectLatest { arePermissionsGranted ->
                    if (arePermissionsGranted) {
                        mainViewModel.searchDevices(applicationContext = application)
                    }
                }
            }
        }
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                mainViewModel.devicesFlow.asFlow().collect { devices ->
                    if (!listOfDevices.contains(devices)) {
                        listOfDevices.add(devices)
                    }
                    withContext(Dispatchers.Main) {
                        tv.text = listOfDevices.toString()
                    }
                }
            }
        }
    }
}
