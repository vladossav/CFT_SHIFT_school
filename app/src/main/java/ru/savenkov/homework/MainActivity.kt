package ru.savenkov.homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_holder, MainFragment())
                .commit()
        }

        findViewById<ImageButton>(R.id.add_button).setOnClickListener {
            if (supportFragmentManager.backStackEntryCount > 0) return@setOnClickListener
            supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(
                    R.anim.bottom_up,
                    0,
                    0,
                    R.anim.up_bottom
                )
                .add(R.id.fragment_holder, AddFragment())
                .commit()
        }
    }
}
