package ru.savenkov.homework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cactivity)

        findViewById<Button>(R.id.enter_a_activity_btn)
            .setOnClickListener {
                val intent = Intent(this, AActivity::class.java)
                startActivity(intent)
            }
    }
}