package ru.savenkov.homework

import ru.savenkov.homework.data.DataSource
import ru.savenkov.homework.data.ListItem
import javax.inject.Inject

class LocalDataSource @Inject constructor(): DataSource {

    override suspend fun getData(): List<ListItem> = listOf(
        ListItem.StudentItem(
            name = "Иван",
            secondName = "Иванов",
            description = "Только что выпустился из универа, с Android знаком не сильно",
            hasPortfolio = true,
        ),
        ListItem.BannerItem(
            title = "Новая заявка",
            description = "Здравствуйте, меня зовут Глеб, ещё не поздно подать заявку?"
        ),
        ListItem.StudentItem(
            name = "Пётр",
            secondName = "Петров",
            description = "Сеньор-помидор, 30 лет опыта С++, хочу попробовать себя в новом направлении",
            hasPortfolio = false,
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
        ),
    )
}