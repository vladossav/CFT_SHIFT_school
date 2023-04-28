package ru.savenkov.homework

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase

import org.junit.Test
import org.junit.Rule


class MainTest: TestCase() {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun checkClickableFields() {
        run {
            MainScreen {
                searchByBin("45717360")

                country {
                    isClickable()
                }
                bankPhone {
                    isClickable()
                }
                bankUrl {
                    isClickable()
                }
            }
        }
    }

    @Test
    fun checkEmptyRecentList() {
        run {
            MainScreen {
                clearRecentListButton.click()
                recentList {
                    isNotDisplayed()
                }
            }
        }
    }

    @Test
    fun checkFilledRecentList() {
        val listOfQueries = listOf("45717360", "457155", "518960", "4444", "51892")
        run {
            MainScreen {
                clearRecentListButton.click()
                listOfQueries.forEach { query ->
                    searchByBin(query)
                }

                listOfQueries
                    .reversed()
                    .forEachIndexed { index, query ->
                    recentList {
                        childAt<MainScreen.RecentItem>(index) {
                            number {
                                isDisplayed()
                                hasText(query)
                            }
                        }
                    }

                }
            }
        }
    }
}
