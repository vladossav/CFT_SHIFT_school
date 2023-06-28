package ru.savenkov.homework.screens.createLoan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.savenkov.homework.R
import ru.savenkov.homework.databinding.FragmentCreateSuccessBinding

class CreateSuccessFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCreateSuccessBinding.inflate(inflater, container, false)

        binding.acceptButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }


}