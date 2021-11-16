package com.mutualmobile.kmmblepoc.android.utils

import android.content.Context
import android.widget.Toast

fun Context.shortToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
