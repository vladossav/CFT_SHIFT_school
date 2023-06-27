package ru.savenkov.homework.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import ru.savenkov.homework.utils.Result
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.savenkov.homework.data.LoanRepository
import ru.savenkov.homework.data.model.Auth
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoanRepository
): ViewModel() {
    var username: String = ""
    var password: String = ""
    val uiState: LiveData<Result<String>>
        get() = _uiState

    private val _uiState = MutableLiveData<Result<String>>(Result.Initial)


    fun login() {
        if (username.isBlank() || password.isBlank()) {
            _uiState.value = Result.Error("Некорректный ввод! Попробуйте снова")
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val auth = Auth(username, password)
            val result = repository.login(auth)
            _uiState.postValue(result)
        }
    }

    fun setInitState() {
        _uiState.value = Result.Initial
    }
}