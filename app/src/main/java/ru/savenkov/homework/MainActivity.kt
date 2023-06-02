package ru.savenkov.homework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import ru.savenkov.homework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.timeString.observe(this) {
            binding.time.text = it
        }

        binding.startPauseButton.setOnClickListener {
            if (viewModel.timer.isStopped()) {
                viewModel.timer.start()
                binding.startPauseButton.text =  resources.getString(R.string.pause_button)
            } else {
                viewModel.timer.stop()
                binding.startPauseButton.text = resources.getString(R.string.start_button)
            }
        }

        binding.resetButton.setOnClickListener {
            binding.startPauseButton.text = resources.getString(R.string.start_button)
            viewModel.timer.cancel()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val intent = Intent(this, PushNotificationService::class.java)
        val delay = 5000.toLong()
        intent.putExtra(PushNotificationService.DELAY_KEY, delay)
        intent.putExtra(PushNotificationService.TIME_ON_TIMER_KEY, viewModel.timeString.value)
        startService(intent)
    }
}