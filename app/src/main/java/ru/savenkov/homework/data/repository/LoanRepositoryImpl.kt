package ru.savenkov.homework.data.repository

import ru.savenkov.homework.data.LoanRepository
import ru.savenkov.homework.data.datasource.LocalSettingsDataSource
import ru.savenkov.homework.data.datasource.RemoteDataSource
import ru.savenkov.homework.data.model.*
import ru.savenkov.homework.utils.Result
import javax.inject.Inject

class LoanRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localSettingsDataSource: LocalSettingsDataSource
): LoanRepository {

    override fun wasFirstLogin(): Boolean = localSettingsDataSource.wasFirstLogin()

    override fun setWasFirstLogin() {
        localSettingsDataSource.setWasFirstLoginKey()
    }

    override suspend fun getLoanCondition(): Result<LoanCondition> =
        remoteDataSource.getLoanCondition()

    override suspend fun createLoan(loan: LoanRequest): Result<Loan> =
        remoteDataSource.createLoan(loan)

    override suspend fun getLoans(): Result<List<Loan>> {
        val token = localSettingsDataSource.getToken()!!
        return remoteDataSource.getLoanList(token)
    }

    override suspend fun getLoanById(id: Long): Result<Loan> =
        remoteDataSource.getLoanById(id)

    override suspend fun login(auth: Auth): Result<String> {
        val result = remoteDataSource.login(auth)
        if (result is Result.Success) {
            localSettingsDataSource.setAuthorizedTokenAndStatus(result.data)
        }
        return result
    }

    override suspend fun logout() {
        localSettingsDataSource.setLogoutStatus()
    }

    override suspend fun register(auth: Auth): Result<UserRole> =
        remoteDataSource.register(auth)

}