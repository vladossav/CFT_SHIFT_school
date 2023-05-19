package ru.savenkov.homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ru.savenkov.homework.databinding.ActivityMainBinding
import ru.savenkov.homework.presentation.MainState
import ru.savenkov.homework.presentation.MainViewModel
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	@Inject
	lateinit var viewModel: MainViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		(applicationContext as App).appComponent.inject(this)
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		binding.loadButton.setOnClickListener {
			viewModel.loadStrings()
		}

		viewModel.state.observe(this) { newState ->
			renderState(newState)
		}
	}

	private fun renderState(state: MainState) {
		when (state) {
			MainState.Loading -> {
				Toast.makeText(this, "loading", Toast.LENGTH_SHORT).show()
				binding.remoteText.text = ""
				binding.localText.text = ""
			}

			is MainState.Success -> {
				binding.remoteText.text = state.remoteString
				binding.localText.text = state.localString
			}
		}
	}
}