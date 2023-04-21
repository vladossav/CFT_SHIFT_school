package ru.savenkov.homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    val FRAGMENT_A_TAG = "A_FRAGMENT_TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, AFragment(), FRAGMENT_A_TAG)
            .commit()

    }

   /* override fun onBackPressed() {
        val fragment = supportFragmentManager
            .findFragmentByTag(FRAGMENT_A_TAG)
        if (fragment!!.isResumed) finishAffinity()
    }*/
}