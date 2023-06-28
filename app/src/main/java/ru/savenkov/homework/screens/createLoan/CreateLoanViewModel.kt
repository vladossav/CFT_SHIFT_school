package ru.savenkov.homework.screens.createLoan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.savenkov.homework.R
import ru.savenkov.homework.data.model.Loan
import ru.savenkov.homework.data.model.LoanCondition
import ru.savenkov.homework.repository.LoanRepository
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

    val conditionState get() = _conditionState
    val createdLoanState get() = _createdLoanState

    private val _conditionState = MutableLiveData<Result<LoanCondition>>(Result.Initial)
    private val _createdLoanState = MutableLiveData<Result<Loan>>(Result.Initial)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _conditionState.postValue(Result.Loading)
            val result = repository.getLoanCondition()
            _conditionState.postValue(result)
        }
    }

    fun createLoan() = viewModelScope.launch(Dispatchers.IO) {
        if (firstName.isBlank() || lastName.isBlank() || phone.isBlank() || amount.isBlank()) {
            _createdLoanState.postValue(Result.Error(res.getString(R.string.error_incorrect_input)))
            return@launch
        }
        val percent = (conditionState.value as Result.Success).data.percent
        val period = (conditionState.value as Result.Success).data.period
        val loanRequest = LoanRequest(amount.toLong(), firstName, lastName, percent, period, phone)
        val result = repository.createLoan(loanRequest)
        _createdLoanState.postValue(result)
    }

    fun checkAmountIsValid(): Boolean {
        val sum = try {
            amount.toLong()
        } catch (err: java.lang.NumberFormatException) {
            return false
        }
        val maxAmount = (conditionState.value as Result.Success).data.maxAmount
        return (sum in 1.. maxAmount)
    }
}