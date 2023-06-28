package ru.savenkov.homework.screens.loan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.savenkov.homework.repository.LoanRepository
import ru.savenkov.homework.data.model.Loan
import ru.savenkov.homework.utils.Result

class LoanViewModel @AssistedInject constructor(
    @Assisted loanId: Long,
    private val repository: LoanRepository
): ViewModel() {

    private val _uiState = MutableLiveData<Result<Loan>>(Result.Initial)
    val uiState: LiveData<Result<Loan>>
    get() = _uiState

    init {
        viewModelScope.launch (Dispatchers.IO) {
            _uiState.postValue(Result.Loading)
            val result = repository.getLoanById(loanId)
            _uiState.postValue(result)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(loanId: Long): LoanViewModel
    }
}