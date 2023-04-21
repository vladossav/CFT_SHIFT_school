package ru.savenkov.homework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aactivity)

        findViewById<Button>(R.id.enter_b_btn).setOnClickListener {
            val intent = Intent(this, BActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.enter_a_fragment_btn).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}