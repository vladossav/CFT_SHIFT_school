package ru.savenkov.homework.data.datasource

import ru.savenkov.homework.R
import ru.savenkov.homework.data.model.*
import ru.savenkov.homework.data.network.LoanApi
import ru.savenkov.homework.utils.Result
import ru.savenkov.homework.utils.StringResourceProvider
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val loanApi: LoanApi,
    private val res: StringResourceProvider
) {
    private var accessToken: String = ""

    suspend fun login(auth: Auth): Result<String> {
        val response = try {
            loanApi.login(auth)
        } catch (err: Exception) {
            null
        } ?: return Result.Error(res.getString(R.string.error_check_internet_connection))

        return if (response.isSuccessful && response.body() != null) {
            accessToken = response.body()!!
            Result.Success(accessToken)
        } else Result.Error(getErrorByResponseCode(response.code()))
    }

    suspend fun getLoanList(token: String): Result<List<Loan>> {
        val response = try {
            accessToken = token
            loanApi.getLoans(token)
        } catch (err: Exception) {
            null
        } ?: return Result.Error(res.getString(R.string.error_check_internet_connection))

        return if (response.isSuccessful && response.body() != null) {
            Result.Success(response.body()!!)
        } else Result.Error(getErrorByResponseCode(response.code()))
    }

    suspend fun getLoanById(id: Long): Result<Loan> {
        val response = try {
            loanApi.getLoanById(accessToken, id)
        } catch (err: Exception) {
            null
        } ?: return Result.Error(res.getString(R.string.error_check_internet_connection))

        return if (response.isSuccessful && response.body() != null) {
            Result.Success(response.body()!!)
        } else Result.Error(getErrorByResponseCode(response.code()))
    }

    suspend fun getLoanCondition(): Result<LoanCondition> {
        val response = try {
            loanApi.getLoanCondition(accessToken)
        } catch (err: Exception) {
            null
        } ?: return Result.Error(res.getString(R.string.error_check_internet_connection))

        return if (response.isSuccessful && response.body() != null) {
            Result.Success(response.body()!!)
        } else Result.Error(getErrorByResponseCode(response.code()))
    }

    suspend fun createLoan(loan: LoanRequest): Result<Loan> {
        val response = try {
            loanApi.createLoan(accessToken, loan)
        } catch (err: Exception) {
            null
        } ?: return Result.Error(res.getString(R.string.error_check_internet_connection))

        return if (response.isSuccessful && response.body() != null) {
            Result.Success(response.body()!!)
        } else Result.Error(getErrorByResponseCode(response.code()))
    }

    suspend fun register(auth: Auth): Result<UserRole> {
        val response = try {
            loanApi.register(auth)
        } catch (err: Exception) {
            null
        } ?: return Result.Error(res.getString(R.string.error_check_internet_connection))

        return if (response.isSuccessful && response.body() != null) {
            Result.Success(response.body()!!)
        } else Result.Error(getErrorByResponseCode(response.code()))
    }

    private fun getErrorByResponseCode(code: Int): String {
        if (code in 500..600) return res.getString(R.string.error_server)
        return when(code) {
            404 -> res.getString(R.string.error_incorrect_input_login_or_password)
            400 -> res.getString(R.string.error_user_already_exists)
            else -> res.getString(R.string.error_check_internet_connection)
        }
    }
}