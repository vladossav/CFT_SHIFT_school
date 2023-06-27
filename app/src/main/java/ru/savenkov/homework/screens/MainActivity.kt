package ru.savenkov.homework.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.savenkov.homework.R
import ru.savenkov.homework.data.datasource.LocalSettingsDataSource
import ru.savenkov.homework.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var prefs: LocalSettingsDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) setNavGraph()
    }

    private fun setNavGraph() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val graph = navController.navInflater.inflate(R.navigation.auth_graph)
        graph.setStartDestination(
            if (prefs.isAuthorized()) {
                R.id.main_graph
            } else {
                R.id.loginFragment
            }
        )
        navController.graph = graph
    }

}