package ru.savenkov.homework.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT). also { snackbar ->
        snackbar.setAction("OK") {
            snackbar.dismiss()
        }
    }
        .show()
}