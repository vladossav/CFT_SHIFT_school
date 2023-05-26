package ru.savenkov.homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var repository: Repository

    private val viewModel: MainActivityViewModel by viewModels {
        viewModelFactory {
            initializer {
                MainActivityViewModel(repository)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val adapter = StudentAdapter()
        val rv = findViewById<RecyclerView>(R.id.requests_holder)
        rv.adapter = adapter

        viewModel.getStudents()
        viewModel.studentsList.observe(this) {
            adapter.list = it
        }
    }
}