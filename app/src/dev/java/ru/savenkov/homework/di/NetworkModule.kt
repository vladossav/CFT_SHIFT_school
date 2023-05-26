package ru.savenkov.homework.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.mock.MockRetrofit
import ru.savenkov.homework.data.ListItem
import ru.savenkov.homework.network.StudentsApi
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        const val BASE_URL = "https://students.org/"
    }

    @Provides
    @Singleton
    fun provideStudentsApi(): StudentsApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val mockApi = MockRetrofit
            .Builder(retrofit)
            .build()
            .create(StudentsApi::class.java)

        val response = listOf(
            ListItem.StudentItem(
                name = "Иван",
                secondName = "Иванов",
                description = "Только что выпустился из универа, с Android знаком не сильно",
                hasPortfolio = true,
            ),
            ListItem.StudentItem(
                name = "Семён",
                secondName = "Сёменов",
                description = "Прошёл курсы Skillbox, SkillFactory, SkillShare, но не могу найти работу, помогите мне",
                hasPortfolio = false,
            ),
            ListItem.StudentItem(
                name = "Андрей",
                secondName = "Андреев",
                description = "Мне не придумали длинного описания",
                hasPortfolio = true,
            ),
            ListItem.StudentItem(
                name = "Егор",
                secondName = "Егоров",
                description = "Lorem ipsum dolor sit amet ya uchenik mne 19 let",
                hasPortfolio = true,
            )
        )

        return mockApi.returningResponse(response)
    }

}