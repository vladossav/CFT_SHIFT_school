package ru.savenkov.homework.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.savenkov.homework.App
import ru.savenkov.homework.R
import ru.savenkov.homework.databinding.ActivityMainBinding
import ru.savenkov.homework.ui.di.MainComponent


class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	lateinit var mainComponent: MainComponent

	override fun onCreate(savedInstanceState: Bundle?) {
		mainComponent = (applicationContext as App).appComponent
			.mainComponent().create()
		mainComponent.inject(this)

		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		supportFragmentManager.beginTransaction()
			.add(R.id.fragment_holder, FactsFragment())
			.commit()
	}

}