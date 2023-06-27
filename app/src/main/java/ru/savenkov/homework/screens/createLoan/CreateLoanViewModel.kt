package ru.savenkov.homework.screens.createLoan

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.savenkov.homework.R
import ru.savenkov.homework.data.LoanRepository
import ru.savenkov.homework.data.model.Loan
import ru.savenkov.homework.data.model.LoanCondition
import ru.savenkov.homework.data.model.LoanRequest
import ru.savenkov.homework.utils.Result
import ru.savenkov.homework.utils.StringResourceProvider
import javax.inject.Inject

@HiltViewModel
class CreateLoanViewModel @Inject constructor(
    private val repository: LoanRepository,
    private val res: StringResourceProvider
): ViewModel() {
    var firstName: String = ""
    var lastName: String = ""
    var phone: String = ""
    var amount: String = ""

    private val _uiState = MutableLiveData<Result<LoanCondition>>()
    val uiState: LiveData<Result<LoanCondition>>
        get() = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getLoanCondition()
            _uiState.postValue(result)
        }
    }


    fun createLoan() = viewModelScope.launch(Dispatchers.IO) {
        if (firstName.isBlank() || lastName.isBlank() || phone.isBlank() || amount.isBlank()) return@launch
        val percent = (uiState.value as Result.Success).data.percent
        val period = (uiState.value as Result.Success).data.period
        val loanRequest = LoanRequest(amount.toLong(), firstName, lastName, percent, period, phone)
        repository.createLoan(loanRequest)
    }

    fun checkAmountIsValid(): Boolean {
        val sum = try {
            amount.toLong()
        } catch (err: java.lang.NumberFormatException) {
            return false
        }
        val maxAmount = (uiState.value as Result.Success).data.maxAmount
        return (sum in 1.. maxAmount)
    }
}