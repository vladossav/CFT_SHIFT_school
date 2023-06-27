package ru.savenkov.homework.screens.login.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.savenkov.homework.R
import ru.savenkov.homework.data.LoanRepository
import ru.savenkov.homework.data.model.Auth
import ru.savenkov.homework.data.model.UserRole
import ru.savenkov.homework.utils.Result
import ru.savenkov.homework.utils.StringResourceProvider
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val repository: LoanRepository,
    private val res: StringResourceProvider
): ViewModel() {

    var username: String = ""
    var password1: String = ""
    var password2: String = ""
    val uiState: LiveData<Result<UserRole>>
        get() = _uiState

    private val _uiState = MutableLiveData<Result<UserRole>>(Result.Initial)

    fun register() = viewModelScope.launch {
        if (username.isBlank() || password1.isBlank() || password2.isBlank()) {
            _uiState.postValue(Result.Error(res.getString(R.string.error_incorrect_input)))
            return@launch
        }
        if (password1 != password2) {
            _uiState.postValue(Result.Error(res.getString(R.string.reg_error_passwords)))
            return@launch
        }
        val auth = Auth(username, password1)
        val result = repository.register(auth)
        _uiState.postValue(result)
    }

    fun setInitState() {
        _uiState.value = Result.Initial
    }
}