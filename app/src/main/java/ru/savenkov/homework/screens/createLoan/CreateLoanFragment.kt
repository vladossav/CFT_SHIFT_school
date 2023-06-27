package ru.savenkov.homework.screens.createLoan

import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ru.savenkov.homework.utils.Result
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.savenkov.homework.R
import ru.savenkov.homework.data.model.LoanRequest
import ru.savenkov.homework.databinding.FragmentCreateLoanBinding
import ru.savenkov.homework.databinding.FragmentLoanBinding
import ru.savenkov.homework.utils.snackbar
import kotlin.math.withSign

@AndroidEntryPoint
class CreateLoanFragment : Fragment() {

    private val viewModel: CreateLoanViewModel by viewModels()

    private var _binding: FragmentCreateLoanBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateLoanBinding.inflate(inflater, container, false)

        if (savedInstanceState != null) {
            binding.lastnameInput.setText(viewModel.lastName)
            binding.firstnameInput.setText(viewModel.firstName)
            binding.phoneInput.setText(viewModel.phone)
            binding.amountInput.setText(viewModel.amount)
        }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.uiState.observe(viewLifecycleOwner) {state ->

            if (state is Result.Success) {
                binding.conditionText.text =
                    resources.getString(
                        R.string.loan_create_condition,
                        state.data.maxAmount.toString(), state.data.percent, state.data.period
                    )
            }
            if (state is Result.Error) view!!.snackbar(state.message)
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

    private fun saveInputState() {
        viewModel.firstName = binding.firstnameInput.text.toString()
        viewModel.lastName = binding.lastnameInput.text.toString()
        viewModel.phone = binding.phoneInput.text.toString()
        viewModel.amount = binding.amountInput.text.toString()
    }
}