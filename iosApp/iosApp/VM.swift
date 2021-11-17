//
//  VM.swift
//  iosApp
//
//  Created by Anmol Verma on 17/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class VM : ObservableObject, OnUpdateListener {
    func update(list: Blue_falconBluetoothPeripheral?) {
        
    }
    
    var viewmodel = MainViewModel()
    
    init() {
        viewmodel.listener
    }
    
    func somes(){
//        viewmodel.scan()
    }
    
    
    
}
