package ru.savenkov.homework.screens.createLoan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import ru.savenkov.homework.utils.Result
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.savenkov.homework.R
import ru.savenkov.homework.databinding.FragmentCreateLoanBinding
import ru.savenkov.homework.utils.showSnackbar

@AndroidEntryPoint
class CreateLoanFragment : Fragment() {

    private val viewModel: CreateLoanViewModel by viewModels()

    private var _binding: FragmentCreateLoanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateLoanBinding.inflate(inflater, container, false)

        if (savedInstanceState != null) setSavedInputData()

        viewModel.conditionState.observe(viewLifecycleOwner) { state ->
            binding.conditionCard.isVisible = state !is Result.Loading
            binding.createLoanForm.isVisible = state !is Result.Loading
            binding.progressBar.isVisible = state is Result.Loading

            if (state is Result.Error) view!!.showSnackbar(state.message)
            if (state is Result.Success) binding.conditionText.text =
                resources.getString(
                    R.string.loan_create_condition,
                    state.data.maxAmount.toString(), state.data.percent, state.data.period
                )
        }

        viewModel.createdLoanState.observe(viewLifecycleOwner) {state ->
            if (state is Result.Success)
                findNavController().navigate(R.id.action_createLoanFragment_to_successFragment)
            if (state is Result.Error) view!!.showSnackbar(state.message)
        }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.createLoanButton.setOnClickListener {
            createLoan()
        }

        return binding.root
    }


    override fun onPause() {
        super.onPause()
        saveInputState()
    }

    private fun createLoan() {
        saveInputState()
        if (viewModel.firstName.isBlank()) binding.firstnameInput.error = resources.getString(R.string.error_input_field_is_blank)
        if (viewModel.lastName.isBlank()) binding.lastnameInput.error = resources.getString(R.string.error_input_field_is_blank)
        if (viewModel.phone.isBlank()) binding.phoneInput.error = resources.getString(R.string.error_input_field_is_blank)
        if (!viewModel.checkAmountIsValid()) {
            binding.amountInput.error = resources.getString(R.string.error_input_amount)
            return
        }
        viewModel.createLoan()

    }

    private fun setSavedInputData() {
        binding.lastnameInput.setText(viewModel.lastName)
        binding.firstnameInput.setText(viewModel.firstName)
        binding.phoneInput.setText(viewModel.phone)
        binding.amountInput.setText(viewModel.amount)
    }

    private fun saveInputState() {
        viewModel.firstName = binding.firstnameInput.text.toString()
        viewModel.lastName = binding.lastnameInput.text.toString()
        viewModel.phone = binding.phoneInput.text.toString()
        viewModel.amount = binding.amountInput.text.toString()
    }
}