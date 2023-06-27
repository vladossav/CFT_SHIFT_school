package ru.savenkov.homework.screens.loans

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.savenkov.homework.data.LoanRepository
import ru.savenkov.homework.data.model.Auth
import ru.savenkov.homework.data.model.Loan
import ru.savenkov.homework.utils.Result
import javax.inject.Inject

@HiltViewModel
class LoansViewModel @Inject constructor(
    private val repository: LoanRepository
    ): ViewModel() {

    private val _loansState: MutableLiveData<Result<List<Loan>>> = MutableLiveData()
    val loansState: LiveData<Result<List<Loan>>>
    get() = _loansState

    fun getLoans() {
        viewModelScope.launch(Dispatchers.IO) {
            val loansList = repository.getLoans()
            _loansState.postValue(loansList)
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.logout()
        }
    }

    fun wasFirstLogin(): Boolean = repository.wasFirstLogin()

    fun setWasFirstLogin() {
        repository.setWasFirstLogin()
    }

    fun checkWasFirstLogin(): Boolean {
        return if (repository.wasFirstLogin()) true
           else {
            repository.setWasFirstLogin()
            false
        }
    }
}