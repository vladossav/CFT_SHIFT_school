package ru.savenkov.homework.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface ConvertApi {
    @Streaming
    @Multipart
    @Headers("Authentication:Basic 649540400 ty4bFdjB218HLmKs")
    @POST("/upload")
    suspend fun uploadFile(
        @Part filePart: MultipartBody.Part
    ): Response<FileResponse>

    companion object {
        const val BASE_URL = "https://v2.convertapi.com"

        fun create() : ConvertApi {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ConvertApi::class.java)
        }

    }
}