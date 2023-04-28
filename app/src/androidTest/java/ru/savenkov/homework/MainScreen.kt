package ru.savenkov.homework

import android.view.KeyEvent
import android.view.View
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.searchview.KSearchView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView


object MainScreen: Screen<MainScreen>() {

    val recentList = KRecyclerView(
        builder = { withId(R.id.recent_rv) },
        itemTypeBuilder = { itemType(::RecentItem) }
    )
    val clearRecentListButton = KButton {withId(R.id.clear_btn)}
    val country = KTextView {withId(R.id.country_data)}
    val bankUrl = KTextView {withId(R.id.bank_url_data)}
    val bankPhone = KTextView {withId(R.id.bank_phone_data)}

    class RecentItem(parent: org.hamcrest.Matcher<View>) : KRecyclerItem<RecentItem>(parent) {
        val number = KTextView(parent) { withId(R.id.rv_text) }
    }

    val search = KSearchView {withId(R.id.search_field)}

    fun searchByBin(bin: String) {
        search {
            typeQuery(bin)
        }
        pressKey(KeyEvent.KEYCODE_ENTER)
        Thread.sleep(4000)
    }
}