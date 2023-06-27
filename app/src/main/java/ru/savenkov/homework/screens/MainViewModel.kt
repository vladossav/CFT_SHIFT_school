package ru.savenkov.homework.screens

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.savenkov.homework.data.datasource.LocalSettingsDataSource
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val prefs: LocalSettingsDataSource
): ViewModel() {

}