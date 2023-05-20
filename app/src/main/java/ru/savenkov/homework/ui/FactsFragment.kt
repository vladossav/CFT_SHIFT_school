package ru.savenkov.homework.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ru.savenkov.homework.databinding.FragmentFactsBinding
import ru.savenkov.homework.di.FragmentScope
import ru.savenkov.homework.presentation.FactsViewModel
import ru.savenkov.homework.presentation.MainState
import ru.savenkov.homework.ui.di.FactsComponent
import javax.inject.Inject

class FactsFragment : Fragment() {
    private lateinit var binding: FragmentFactsBinding
    @FragmentScope
    @Inject
    lateinit var viewModel: FactsViewModel
    lateinit var factComponent: FactsComponent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFactsBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.loadButton.setOnClickListener {
            viewModel.loadStrings()
        }

        viewModel.state.observe(viewLifecycleOwner) { newState ->
            renderState(newState)
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        factComponent = (activity as MainActivity).mainComponent.factComponent().create()
        factComponent.inject(this)
    }

    private fun renderState(state: MainState) {
        when (state) {
            MainState.Loading -> {
                Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
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