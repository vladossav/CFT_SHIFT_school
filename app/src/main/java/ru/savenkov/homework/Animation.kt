package ru.savenkov.homework

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.LinearInterpolator

fun View.makeWiggle() {
    ObjectAnimator.ofFloat(this, View.TRANSLATION_X, 0f, 20f, -40f, 0f).apply {
        duration = 500
        interpolator = LinearInterpolator()
        start()
    }
}

fun View.fadeIn() {
    ObjectAnimator.ofFloat(this, View.ALPHA, 0f, 1f).apply {
        duration = 600
        interpolator = LinearInterpolator()
        start()
    }
}