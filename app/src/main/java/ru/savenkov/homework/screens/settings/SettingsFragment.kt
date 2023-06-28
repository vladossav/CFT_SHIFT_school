package ru.savenkov.homework.screens.settings

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.savenkov.homework.R
import ru.savenkov.homework.data.datasource.LocalSettingsDataSource
import ru.savenkov.homework.databinding.FragmentSettingsBinding
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : DialogFragment() {
    @Inject
    lateinit var prefs: LocalSettingsDataSource

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.currentLanguage.text = prefs.getCurrentLanguage()
        binding.languagePanel.setOnClickListener{
            showLanguageMenu()
        }

        return  binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun showLanguageMenu() {
        val popUpMenu = PopupMenu(context, view)
        popUpMenu.menuInflater.inflate(R.menu.language_menu, popUpMenu.menu)
        popUpMenu.setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.ru -> updateLocale("ru")
                R.id.en -> updateLocale("en")
            }
            true
        }
        popUpMenu.show()
    }

    private fun updateLocale(languageCode: String) {
        prefs.setCurrentLanguage(languageCode)
        activity!!.recreate()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}