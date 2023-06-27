package ru.savenkov.homework.screens.instruction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.savenkov.homework.R
import ru.savenkov.homework.databinding.FragmentInstructionBinding

@AndroidEntryPoint
class InstructionFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentInstructionBinding.inflate(inflater, container, false)

        binding.closeButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }


}