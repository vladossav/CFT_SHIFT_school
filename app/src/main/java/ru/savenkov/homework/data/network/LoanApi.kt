package ru.savenkov.homework.data.network

import retrofit2.Response
import retrofit2.http.*
import ru.savenkov.homework.data.model.*


interface LoanApi {

    @POST("login")
    suspend fun login(@Body auth: Auth): Response<String>

    @POST("registration")
    suspend fun register(@Body auth: Auth): Response<UserRole>

    @POST("loans")
    suspend fun createLoan(@Header("Authorization") token: String, @Body loan: LoanRequest): Response<Loan>

    @GET("loans/all")
    suspend fun getLoans(@Header("Authorization") token: String): Response<List<Loan>>

    @GET("loans/{id}")
    suspend fun getLoanById(@Header("Authorization") token: String, @Path("id") id: Long): Response<Loan>

    @GET("loans/conditions")
    suspend fun getLoanCondition(@Header("Authorization") token: String): Response<LoanCondition>
}