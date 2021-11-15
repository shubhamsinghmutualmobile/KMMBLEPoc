package com.mutualmobile.kmmblepoc.android.utils

import android.app.Activity
import android.widget.Toast

fun Activity.shortToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
