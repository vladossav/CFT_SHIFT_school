package ru.savenkov.homework.data.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.savenkov.homework.data.LoanRepository
import ru.savenkov.homework.data.repository.LoanRepositoryImpl
import ru.savenkov.homework.utils.StringResourceProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    companion object {

        @Singleton
        @Provides
        fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
            return context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        }

        @Provides
        fun provideStringResourceProvider(@ApplicationContext context: Context) : StringResourceProvider =
            StringResourceProvider(context)
    }

    @Singleton
    @Binds
    fun bindLoanRepository(impl: LoanRepositoryImpl): LoanRepository

}