package ru.savenkov.homework

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    val timeString = MutableLiveData("00:00")
    val timer = Timer {time ->
        timeString.postValue(time)
    }
}