package ru.savenkov.homework.data

import ru.savenkov.homework.data.model.*
import ru.savenkov.homework.utils.Result

interface LoanRepository {
    suspend fun getLoans(): Result<List<Loan>>
    suspend fun getLoanById(id: Long): Result<Loan>
    suspend fun getLoanCondition(): Result<LoanCondition>
    suspend fun createLoan(loan: LoanRequest): Result<Loan>

    suspend fun login(auth: Auth): Result<String>
    suspend fun register(auth: Auth): Result<UserRole>
    suspend fun logout()
    fun wasFirstLogin(): Boolean
    fun setWasFirstLogin()
}