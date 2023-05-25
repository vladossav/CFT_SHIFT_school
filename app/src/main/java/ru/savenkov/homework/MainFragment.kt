package ru.savenkov.homework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class MainFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        view.findViewById<RecyclerView>(R.id.requests_holder).apply {
            val adapter = StudentAdapter()
            adapter.list = data
            this.adapter = adapter
        }

        return view
    }
}

val data: List<ListItem> = listOf(
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